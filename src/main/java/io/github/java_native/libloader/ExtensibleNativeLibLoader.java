/*
 * Copyright (c) 2020 the 'java-native' development team
 *         https://github.com/java-native/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.java_native.libloader;

import io.github.java_native.libloader.config.LibLoaderConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implemnentation of LibLoader which detects system configuration via ServiceLoaders.
 */
class ExtensibleNativeLibLoader extends AbstractExtensibleNativeLibLoader implements NativeLibLoader {

    public ExtensibleNativeLibLoader(final LibLoaderConfig config) {
        super(config);
    }

    @Override
    public LibLoaderResult loadLibrary(final String libraryName) {
        ensureSystemDetected();

        return null;
    }

    protected List<String> getLibraryPackagePath(final String libName) {
        ensureSystemDetected();

        return getConfig().getLibraryPathFormatter().getFormattedPaths(getDetectedSystem(), libName);
    }

    protected List<String> getLibraryPackagePath(final String libName, final String version) {
        ensureSystemDetected();

        final List<String> formattedPaths = getConfig().getLibraryPathFormatter().getFormattedPaths(getDetectedSystem(), libName, version);
        final List<String> formattedPathsWoVersion = getConfig().getLibraryPathFormatter().getFormattedPaths(getDetectedSystem(), libName);

        final List<String> paths = new ArrayList<String>(formattedPaths.size() + formattedPathsWoVersion.size());
        paths.addAll(formattedPaths);
        paths.addAll(formattedPathsWoVersion);

        return Collections.unmodifiableList(paths);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtensibleNativeLibLoader{");
        sb.append("super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
