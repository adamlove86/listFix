package listfix.model.playlists;

import io.github.borewit.lizzy.content.Content;
import io.github.borewit.lizzy.playlist.Media;
import listfix.comparators.MatchedPlaylistEntryComparator;
import listfix.io.IPlaylistOptions;
import listfix.model.enums.PlaylistEntryStatus;
import listfix.util.FileNameTokenizer;
import listfix.view.support.IProgressObserver;
import listfix.view.support.ProgressAdapter;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class PlaylistEntry implements Cloneable
{
  // logger
  /**
   * The list of folders we know don't exist.
   */
  public static List<String> NonExistentDirectories = new ArrayList<>();

  // Status of this item.
  protected PlaylistEntryStatus _status = PlaylistEntryStatus.Exists;

  // Has this item been fixed?
  protected boolean _isFixed;

  // The file this entry belongs to.
  protected final Playlist playlist;
  protected final Media media;

  private static final Pattern APOS_PATTERN = Pattern.compile("'");
  private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("(([a-z])([A-Z]))");

  /**
   * Returns the _status.
   */
  public PlaylistEntryStatus getStatus()
  {
    return _status;
  }

  // Construct an M3U URL entry.

  protected PlaylistEntry(Playlist playlist, Media media)
  {
    this.playlist = playlist;
    this.media = media;
  }

  protected void copyTo(PlaylistEntry target)
  {
    target._status = this._status;
  }

  public void markFixedIfFound()
  {
    if (getStatus() == PlaylistEntryStatus.Found)
    {
      _isFixed = true;
    }
  }

  public Media getMedia()
  {
    return this.media;
  }

  public abstract String getTrackFolder();

  public abstract String getTrackFileName();

  // check the file system for existence if we don't already know the file exists.
  protected abstract boolean exists();

  public abstract void recheckFoundStatus();

  public boolean isFound()
  {
    return getStatus() == PlaylistEntryStatus.Found;
  }

  public boolean isFixed()
  {
    return _isFixed;
  }

  public void setFixed(boolean fixed)
  {
    _isFixed = fixed;
  }

  public abstract boolean isURL();

  public abstract boolean isRelative();

  private final MatchedPlaylistEntryComparator matchedPlaylistEntryComparator = new MatchedPlaylistEntryComparator();

  public List<PotentialPlaylistEntryMatch> findClosestMatches(Collection<String> mediaFiles, IProgressObserver<String> observer, IPlaylistOptions playListOptions)
  {
    ProgressAdapter<String> progress = new ProgressAdapter<>(observer);
    progress.setTotal(mediaFiles.size());

    // Remove apostrophes and addAt spaces between lowercase and capital letters so we can tokenize by camel case.
    String entryName = CAMEL_CASE_PATTERN.matcher(APOS_PATTERN.matcher(getTrackFileName()).replaceAll("")).replaceAll("$2 $3").toLowerCase();

    return mediaFiles.stream().map(mediaFilePath -> {

        progress.stepCompleted();

        final File mediaFile = new File(mediaFilePath);
        final int score = new FileNameTokenizer(playListOptions).score(entryName, CAMEL_CASE_PATTERN.matcher(APOS_PATTERN.matcher(mediaFile.getName()).replaceAll("")).replaceAll("$2 $3").toLowerCase());

        return new PotentialPlaylistEntryMatch(mediaFile.toPath(), score);
      })
      .sorted(matchedPlaylistEntryComparator)
      .limit(playListOptions.getMaxClosestResults())
      .collect(Collectors.toList());
  }

  @Override
  public abstract Object clone();

  public static PlaylistEntry makePlaylistEntry(Playlist playlist, Media media)
  {
    Content content = media.getSource();
    try
    {
      URI uri = content.getURI();
      if (!Objects.equals(uri.getScheme(), "file"))
      {
        return new UriPlaylistEntry(playlist, media);
      }
    }
    catch (URISyntaxException e)
    {
      // ignore
    }
    return new FilePlaylistEntry(playlist, media);
  }
}
