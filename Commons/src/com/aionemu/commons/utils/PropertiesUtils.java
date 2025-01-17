/**
 * This file is part of Aion X Emu <aionxemu.com>
 *
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aionemu.commons.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * This class is designed to simplify routine job with properties
 *
 * @author SoulKeeper
 */
public class PropertiesUtils {
    /**
     * Loads properties by given file
     *
     * @param file filename
     * @return loaded properties
     * @throws java.io.IOException if can't load file
     */
    public static Properties load(String file) throws IOException {
        return load(new File(file));
    }

    /**
     * Loads properties by given file
     *
     * @param file filename
     * @return loaded properties
     * @throws java.io.IOException if can't load file
     */
    public static Properties load(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Properties p = new Properties();
        p.load(fis);
        fis.close();
        return p;
    }

    /**
     * Loades properties from given files
     *
     * @param files list of string that represents files
     * @return array of loaded properties
     * @throws IOException if was unable to read properties
     */
    public static Properties[] load(String... files) throws IOException {
        Properties[] result = new Properties[files.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = load(files[i]);
        }
        return result;
    }

    /**
     * Loades properties from given files
     *
     * @param files list of files
     * @return array of loaded properties
     * @throws IOException if was unable to read properties
     */
    public static Properties[] load(File... files) throws IOException {
        Properties[] result = new Properties[files.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = load(files[i]);
        }
        return result;
    }

    /**
     * Loads non-recursively all .property files form directory
     *
     * @param dir string that represents directory
     * @return array of loaded properties
     * @throws IOException if was unable to read properties
     */
    public static Properties[] loadAllFromDirectory(String dir) throws IOException {
        return loadAllFromDirectory(new File(dir), false);
    }

    /**
     * Loads non-recursively all .property files form directory
     *
     * @param dir directory
     * @return array of loaded properties
     * @throws IOException if was unable to read properties
     */
    public static Properties[] loadAllFromDirectory(File dir) throws IOException {
        return loadAllFromDirectory(dir, false);
    }

    /**
     * Loads all .property files form directory
     *
     * @param dir       string that represents directory
     * @param recursive parse subdirectories or not
     * @return array of loaded properties
     * @throws IOException if was unable to read properties
     */
    public static Properties[] loadAllFromDirectory(String dir, boolean recursive) throws IOException {
        return loadAllFromDirectory(new File(dir), recursive);
    }

    /**
     * Loads all .property files form directory
     *
     * @param dir       directory
     * @param recursive parse subdirectories or not
     * @return array of loaded properties
     * @throws IOException if was unable to read properties
     */
    @SuppressWarnings("unchecked")
    public static Properties[] loadAllFromDirectory(File dir, boolean recursive) throws IOException {
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"properties"}, recursive);
        return load(files.toArray(new File[files.size()]));
    }
}
