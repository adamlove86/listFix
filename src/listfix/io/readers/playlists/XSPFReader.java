/*
 *  listFix() - Fix Broken Playlists!
 *  Copyright (C) 2001-2012 Jeremy Caron
 * 
 *  This file is part of listFix().
 * 
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, please see http://www.gnu.org/licenses/
 */

package listfix.io.readers.playlists;

import christophedelory.playlist.SpecificPlaylist;
import christophedelory.playlist.SpecificPlaylistFactory;
import christophedelory.playlist.xspf.Track;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import listfix.model.playlists.PlaylistEntry;
import listfix.model.enums.PlaylistType;
import listfix.util.ExStack;
import listfix.util.UnicodeUtils;
import listfix.view.support.IProgressObserver;
import listfix.view.support.ProgressAdapter;

import org.apache.log4j.Logger;

/**
 * Reads in a XSPF file and returns a List containing PlaylistEntries that represent the files & URIs in the playlist.
 * @author jcaron
 */
public class XSPFReader implements IPlaylistReader
{
	private File _listFile;
	private String _encoding;
	
	private static final PlaylistType type = PlaylistType.XSPF;
	private static final Logger _logger = Logger.getLogger(XSPFReader.class);
	
	/**
	 * 
	 * @param inputFile
	 */
	public XSPFReader(File inputFile)
	{
		_listFile = inputFile;		
		_encoding = UnicodeUtils.getEncoding(_listFile);
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getEncoding()
	{
		return _encoding;
	}

	/**
	 * 
	 * @param encoding
	 */
	@Override
	public void setEncoding(String encoding)
	{
		_encoding = encoding;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public PlaylistType getPlaylistType()
	{
		return type;
	}

	/**
	 * 
	 * @param observer 
	 * @return
	 * @throws IOException
	 */
	@Override
	public List<PlaylistEntry> readPlaylist(IProgressObserver observer) throws IOException
	{
		// Init a progress adapter if we have a progress observer.
		ProgressAdapter progress = null;
		if (observer != null) 
		{
			progress = ProgressAdapter.wrap(observer);
		} 
		
		List<PlaylistEntry> entriesList = new ArrayList<>();
		URI uri; 
		File trackFile;
		SpecificPlaylist loadedList = SpecificPlaylistFactory.getInstance().readFrom(_listFile);
		if (loadedList.getProvider().getId().equals("xspf"))
		{
			christophedelory.playlist.xspf.Playlist xspfList = (christophedelory.playlist.xspf.Playlist)SpecificPlaylistFactory.getInstance().readFrom(_listFile);
			
			// Set the total if we have an observer.
			if (progress != null)
			{
				progress.setTotal(xspfList.getTracks().size());
			}
			
			int trackCount = 0;
			for (Track track : xspfList.getTracks())
			{
				if (observer != null)
				{
					if (observer.getCancelled())
					{
						// Bail out if the user cancelled.
						return null;
					}
				}
				
				try
				{
					uri = new URI(track.getStringContainers().get(0).getText());
					try
					{
						trackFile = new File(uri.getSchemeSpecificPart());
						if (trackFile != null)
						{
							entriesList.add(new PlaylistEntry(trackFile, getTitle(track), track.getDuration() != null ? track.getDuration().longValue() : -1, _listFile));
						}
					}
					catch (Exception e)
					{
						// if that didn't work, then we're probably dealing w/ a URL
						entriesList.add(new PlaylistEntry(uri, track.getTitle(), track.getDuration() != null ? track.getDuration().longValue() : -1));
					}
				}
				catch (Exception ex)
				{
					_logger.error("[XSPFReader] - Could not convert lizzy entry to PlaylistEntry - " + ExStack.toString(ex), ex);
				}
				
				if (progress != null)
				{
					// Step forward if we have an observer.
					progress.setCompleted(trackCount++);
				}
			}
			return entriesList;
		}
		else
		{
			throw new IOException("XSPF file was not in XSPF format!!");
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@Override
	public List<PlaylistEntry> readPlaylist() throws IOException
	{
		List<PlaylistEntry> entriesList = new ArrayList<>();
		URI uri; 
		File trackFile;
		SpecificPlaylist loadedList = SpecificPlaylistFactory.getInstance().readFrom(_listFile);
		if (loadedList.getProvider().getId().equals("xspf"))
		{
			christophedelory.playlist.xspf.Playlist xspfList = (christophedelory.playlist.xspf.Playlist)SpecificPlaylistFactory.getInstance().readFrom(_listFile);
			for (Track track : xspfList.getTracks())
			{
				try
				{
					uri = new URI(track.getStringContainers().get(0).getText());
					try
					{
						trackFile = new File(uri.getSchemeSpecificPart());
						if (trackFile != null)
						{
							entriesList.add(new PlaylistEntry(trackFile, getTitle(track), track.getDuration() != null ? track.getDuration().longValue()  : -1, _listFile));
						}
					}
					catch (Exception e)
					{
						// if that didn't work, then we're probably dealing w/ a URL
						entriesList.add(new PlaylistEntry(uri, track.getTitle(), track.getDuration() != null ? track.getDuration().longValue()  : -1));
					}
				}
				catch (Exception ex)
				{
					_logger.error("[XSPFReader] - Could not convert lizzy entry to PlaylistEntry - " + ExStack.toString(ex), ex);
				}
			}
			return entriesList;
		}
		else
		{
			throw new IOException("XSPF file was not in XSPF format!!");
		}
	}

	private String getTitle(Track track)
	{
		if (track.getCreator() != null && track.getTitle() != null)
		{
			return track.getCreator() + " - " + track.getTitle();
		}
		else if (track.getCreator() == null && track.getTitle() != null)
		{
			return track.getTitle();
		}
		else if (track.getCreator() != null && track.getTitle() == null)
		{
			return track.getCreator();
		}
		return "";
	}
}
