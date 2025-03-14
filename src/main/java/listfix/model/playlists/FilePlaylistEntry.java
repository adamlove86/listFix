package listfix.model.playlists;

import io.github.borewit.lizzy.content.Content;
import io.github.borewit.lizzy.playlist.Media;
import listfix.config.IMediaLibrary;
import listfix.io.FileUtils;
import listfix.model.enums.PlaylistEntryStatus;
import listfix.util.ArrayFunctions;
import listfix.util.OperatingSystem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Collection;

public class FilePlaylistEntry extends PlaylistEntry
{
  // This entry's File object.
  private static final boolean isWindows = File.separatorChar == '\\';
  protected Path trackPath;
  protected Path playlistPath;

  public FilePlaylistEntry(Playlist playlist, Media media)
  {
    super(playlist, media);
    // Use the raw string from media source for file system checks
    this.trackPath = convertPath(Path.of(media.getSource().toString()));
    this.playlistPath = playlist.getPath();
    // should we skip the exists check?
    if (this.skipExistsCheck())
    {
      _status = PlaylistEntryStatus.Missing;
    }
    // if we should check, do so...
    else if (this.exists())
    {
      // file was found in its current location
      _status = PlaylistEntryStatus.Found;
    }
    else
    {
      // file was not found
      if (!this.trackPath.isAbsolute())
      {
        if (OperatingSystem.isWindows())
        {
          // try one more thing, winamp creates some lists with pseudo-relative paths on Windows
          Path reconstructedTrackPath = playlistPath.getRoot().resolve(trackPath);

          Files.exists(reconstructedTrackPath);

          if (Files.exists(reconstructedTrackPath))
          {
            _status = PlaylistEntryStatus.Found;
            this.trackPath = playlistPath.relativize(reconstructedTrackPath);
          }
          else
          {
            _status = PlaylistEntryStatus.Missing;
          }
        }
        else
        {
          _status = PlaylistEntryStatus.Missing;
        }
      }
      else
      {
        _status = PlaylistEntryStatus.Missing;
      }
    }
  }

  private static Path convertPath(Path path)
  {
    // Use raw string for file system path conversion
    String pathAsString = path.toString();
    if (isWindows)
    {
      if (pathAsString.startsWith("/") || pathAsString.startsWith("../") || pathAsString.startsWith("./"))
      {
        pathAsString = pathAsString.replace("/", "\\");
      }
    }
    else
    {
      if (pathAsString.startsWith("\\") || pathAsString.startsWith("..\\") || pathAsString.startsWith(".\\"))
      {
        pathAsString = pathAsString.replace("\\", "/");
      }
    }
    return Path.of(pathAsString);
  }

  /**
   * Normalize the filename or path string to NFC form.
   *
   * @param filename String to normalize.
   * @return Normalized string.
   */
  public static String normalizeFilename(String filename) {
    return filename == null ? null : Normalizer.normalize(filename, Form.NFC);
  }

  /**
   * Update the filename portion of the track path.
   *
   * @param filename New filename to assign.
   */
  public void setFileName(String filename)
  {
    // Use the raw filename for file system operations; normalization is applied upon retrieval
    this.trackPath = this.trackPath.getParent().resolve(filename);
    this.recheckFoundStatus();
  }

  /**
   * Todo: rename to resolved path.
   *
   * @return Resolved path, relative to the playlist folder.
   */
  public Path getAbsolutePath()
  {
    Path absolutePath = this.trackPath.isAbsolute() ? this.trackPath : this.playlistPath.getParent().resolve(this.trackPath).normalize();
    System.out.println("Computed absolute path: " + absolutePath);
    return absolutePath;
  }

  private boolean skipExistsCheck()
  {
    String[] emptyPaths = new String[NonExistentDirectories.size()];
    NonExistentDirectories.toArray(emptyPaths);
    return isFound() || ArrayFunctions.containsStringPrefixingAnotherString(emptyPaths, this.getTrackFolder(), false);
  }

  public boolean findNewLocationFromFileList(Collection<String> fileList, boolean caseInsensitiveExactMatching, boolean useRelativePath)
  {
    String fileSearchResult = null;
    // Normalize the file name for internal comparison
    String trimmedFileName = normalizeFilename(this.getTrackFileName().trim());
    boolean caseSensitiveMatching = !isWindows && !caseInsensitiveExactMatching;
    String candidateFileName;
    for (String file : fileList)
    {
      // Get the filename from the media library entry for direct comparison (normalized)
      candidateFileName = normalizeFilename(Path.of(file).getFileName().toString());
      if (caseSensitiveMatching ? candidateFileName.equals(trimmedFileName) : candidateFileName.equalsIgnoreCase(trimmedFileName))
      {
        fileSearchResult = file;
        break;
      }
    }
    if (fileSearchResult != null)
    {
      this.trackPath = Path.of(fileSearchResult);
      if (useRelativePath)
      {
        this.trackPath = FileUtils.getRelativePath(this.trackPath, this.playlistPath);
      }
      this.recheckFoundStatus();
      _isFixed = _status == PlaylistEntryStatus.Found;
      return true;
    }
    return false;
  }

  public boolean updatePathToMediaLibraryIfFoundOutside(IMediaLibrary dirLists, boolean caseInsensitiveExactMatching, boolean useRelativePath)
  {
    if (_status == PlaylistEntryStatus.Found && !ArrayFunctions.containsStringPrefixingAnotherString(dirLists.getMediaDirectories(), this.getTrackFolder(), FilePlaylistEntry.isWindows))
    {
      return findNewLocationFromFileList(dirLists.getNestedMediaFiles(), caseInsensitiveExactMatching, useRelativePath);
    }
    return false;
  }

  public Path getTrackPath()
  {
    return this.trackPath;
  }

  public void setTrackPath(Path trackPath)
  {
    this.trackPath = trackPath;
  }

  public Path getPlaylistPath()
  {
    return this.playlistPath;
  }

  public void update(Path track)
  {
    this.trackPath = track;
    // Use raw track string for file system checks; normalization is applied later upon retrieval
    Content content = new Content(track.toString());
    this.media.setSource(content);
  }

  @Override
  protected boolean exists()
  {
    return Files.exists(this.getAbsolutePath());
  }

  @Override
  protected void copyTo(PlaylistEntry target)
  {
    super.copyTo(target);
    if (target instanceof FilePlaylistEntry)
    {
      FilePlaylistEntry filePlaylistEntry = (FilePlaylistEntry) target;
      filePlaylistEntry.trackPath = this.trackPath;
      filePlaylistEntry.playlistPath = this.playlistPath;
    }
  }

  @Override
  public FilePlaylistEntry clone()
  {
    FilePlaylistEntry clone = new FilePlaylistEntry(this.playlist, this.media);
    this.copyTo(clone);
    return clone;
  }

  @Override
  public void recheckFoundStatus()
  {
    _status = this.exists() ? PlaylistEntryStatus.Found : PlaylistEntryStatus.Missing;
  }

  @Override
  public String getTrackFolder()
  {
    Path parent = this.trackPath.getParent();
    // Internal comparisons use normalized strings
    return parent == null ? "" : normalizeFilename(parent.toString());
  }

  @Override
  public String getTrackFileName()
  {
    // Internal comparisons use normalized strings
    return normalizeFilename(this.trackPath.getFileName().toString());
  }

  @Override
  public boolean isURL()
  {
    return false;
  }

  @Override
  public boolean isRelative()
  {
    return !this.trackPath.isAbsolute();
  }

  @Override
  public boolean equals(Object other)
  {
    return other instanceof FilePlaylistEntry &&
      this.getAbsolutePath().equals(((FilePlaylistEntry) other).getAbsolutePath());
  }

  @Override
  public int hashCode()
  {
    return this.trackPath.hashCode();
  }
}
