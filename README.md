[![CI](https://github.com/Borewit/listFix/actions/workflows/ci.yml/badge.svg)](https://github.com/Borewit/listFix/actions/workflows/ci.yml)
[![GitHub all releases](https://img.shields.io/github/downloads/Borewit/listfix/total)](https://github.com/Borewit/listFix/releases)

# listFix() - Playlist Repair Done Right (adamlove86 Fork)

This is a personal fork of Borewit's [listFix](https://github.com/Borewit/listFix). The primary goal is experimentation and adding features.

## Key Differences from Original (Borewit/listFix)

As of the latest commit on this branch, the key additions/changes compared to the upstream `Borewit/listFix` repository are:

- **Unicode Normalization (NFC):** Implemented robust handling of filenames with special characters (e.g., accents like é, ö) across different operating systems by normalizing filenames (NFC) for internal comparisons and matching, while using raw filenames for file system operations. Centralized this logic in a `UnicodeUtils` class.
- **Persistent Missing File Cache:** Added a cache (`%USERPROFILE%\.listFix()\missingFileCache.json`) that remembers manual fixes for missing files across sessions. If a path is fixed in one playlist, it automatically applies to the same missing path in other playlists opened later.
- **Save All Dialog:** Introduced a "File > Save All..." menu option that allows saving all open playlists simultaneously. Users can select the desired output format (M3U, M3U8, PLS, WPL) and character encoding (though encoding enforcement depends on the underlying library).

## History

[listFix](https://github.com/Borewit/listFix) is cloned from [sourceforge.net](http://listfix.sourceforge.net/) using [a reposurgeon based script](https://github.com/Borewit/migrate-listFx).

## Features

- M3U/M3U8/PLS/WPL Support
- Find lost/missing/renamed playlist entries (Exact & Closest Matches)
- Insert/Move/Delete/Replace/Append entries
- Copy/export selected files
- Insert/Append Playlists
- Sort, Randomize, Remove duplicates/missing
- Launch entry/playlist in default media player
- Support for URL & UNC paths
- Save playlists with absolute/relative references
- Winamp media library support (repair/extract)
- **Unicode Normalization (NFC):** Improved handling of special characters in filenames across platforms.
- **Persistent Missing File Cache:** Remembers missing file fixes across sessions.
- **Save All:** Save all open playlists with format/encoding options.

## Requirements

- UNC paths only supported on Windows (Linux users can smbmount a network drive and then use the mount point as a media directory)

## Run the application

From the command line:

```shell
java -jar listFix-2.7.0-all.jar
```

## Development

### Build project

In project folder, run:

```shell
gradlew build
```

### Run application

In project folder, run:

```shell
gradlew run
```

### Build Windows distribution

In project folder, run:

```shell
gradlew jpackage
```

### Build Windows installer

Requires to be build with [OpenJDK JDK 15 Project](https://jdk.java.net/java-se-ri/15).
It may work with other, but the executable did not work building with [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html).

You must install [Inno Setup (iscc)](https://jrsoftware.org/isinfo.php) to generate an EXE installer and
[WIX Toolset (candle and light)](https://wixtoolset.org/) to generate an MSI file.
as explained in the [JavaPackager: Windows tools installation guide](https://github.com/fvarrui/JavaPackager/blob/master/docs/windows-tools-guide.md)

Download and install [WiX Toolset](https://github.com/wixtoolset/wix3).

A quick way to do that is run the following from [cmd shell which is run as administrator](https://www.howtogeek.com/194041/how-to-open-the-command-prompt-as-administrator-in-windows-8.1/):

```cmd
@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
choco install -y innosetup wixtoolset
```

You need to add the Wix binary path (something like `C:\Program Files (x86)\WiX Toolset v3.11\bin`) to your PATH environment variable.

In project folder, run:

```shell
gradlew packageMyApp
```

### File locations

| File                       | Windows path                                    | Linux path                              |
| -------------------------- | ----------------------------------------------- | --------------------------------------- |
| Logfile                    | `%USERPROFILE%\.listFix()\logs\rollingfile.log` | `$HOME/.listFix()/logs/rollingfile.log` |
| Application configuration  | `%USERPROFILE%\.listFix()\options.json`         | `$HOME/.listFix()/options.json`         |
| Cached media library paths | `%USERPROFILE%\.listFix()\mediaLibrary.json`    | `$HOME/.listFix()/mediaLibrary.json`    |
| Which playlist to open     | `%USERPROFILE%\.listFix()\history.json `        | `$HOME/.listFix()/history.json`         |

Other documentation:

- [Release Procedure.md](doc%2FRelease%20Procedure.md)
