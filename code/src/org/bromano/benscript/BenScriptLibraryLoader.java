package org.bromano.benscript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class BenScriptLibraryLoader {

    public static String loadLibraries() throws Exception {
        String libraries = "";

//        // TODO: Figure out why loading directory doesn't work for jar file
//        InputStream libraryFileStream = BenScriptLibraryLoader.class.getResourceAsStream("libraries");
//
//        String[] libraryFileNames = readStream(libraryFileStream).split("\\r?\\n");
//        for (String libraryFileName : libraryFileNames) {
//            if (!libraryFileName.contains(".bs")) {
//                continue;
//            }
//
//            InputStream stream = BenScriptLibraryLoader.class.getResourceAsStream("libraries/" + libraryFileName);
//            libraries += readStream(stream) + "\n";
//        }

        InputStream stream = BenScriptLibraryLoader.class.getResourceAsStream("libraries/dictionary.lib.bs");
        libraries += readStream(stream) + "\n";
        return libraries;
    }

    public static String readStream(InputStream stream) throws Exception {
        StringBuilder sb = new StringBuilder();

        try {
            Reader r = new InputStreamReader(stream);
            int c;

            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (Exception e) {
            throw new Exception("Failed to read stream!");
        }

        return sb.toString();
    }
}
