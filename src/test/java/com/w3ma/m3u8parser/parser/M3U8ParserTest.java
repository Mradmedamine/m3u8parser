package com.w3ma.m3u8parser.parser;

import com.w3ma.m3u8parser.data.Playlist;
import com.w3ma.m3u8parser.exception.PlaylistParseException;
import com.w3ma.m3u8parser.scanner.M3U8ItemScanner;
import com.w3ma.m3u8parser.util.ResourcesUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import static org.junit.Assert.*;


public class M3U8ParserTest {

    private ResourcesUtil resourcesUtil;

    @Before
    public void setup() {
        resourcesUtil = new ResourcesUtil();
    }

    @Test
    public void parseTest() {
        final InputStream workingPlaylist = resourcesUtil.getInputStream(getClass(), "workingPlaylist.m3u8");
        assertNotNull(workingPlaylist);
        final M3U8Parser m3U8Parser = new M3U8Parser(workingPlaylist, M3U8ItemScanner.Encoding.UTF_8);
        try {
            final Playlist playlist = m3U8Parser.parse();
            assertNotNull(playlist);
            assertEquals(3, playlist.getTrackSetMap().size());
            assertEquals(2, playlist.getTrackSetMap().get("A").size());
            assertEquals(1, playlist.getTrackSetMap().get("B").size());
            assertEquals(1, playlist.getTrackSetMap().get("C").size());
        } catch (IOException | ParseException | PlaylistParseException e) {
            // the test has failed
            fail();
        } finally {
            IOUtils.closeQuietly(workingPlaylist);
        }
    }

    @Test
    public void parseTest2() {
        final InputStream workingPlaylist = resourcesUtil.getInputStream(getClass(), "workingPlaylist2.m3u8");
        assertNotNull(workingPlaylist);
        final M3U8Parser m3U8Parser = new M3U8Parser(workingPlaylist, M3U8ItemScanner.Encoding.UTF_8);
        try {
            final Playlist playlist = m3U8Parser.parse();
            assertNotNull(playlist);
            assertEquals(30, playlist.getTrackSetMap().size());
        } catch (IOException | ParseException | PlaylistParseException e) {
            // the test has failed
            fail();
        } finally {
            IOUtils.closeQuietly(workingPlaylist);
        }
    }
}
