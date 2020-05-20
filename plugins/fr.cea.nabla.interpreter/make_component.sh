#!/usr/bin/env bash

readonly JAVA_VERSION="${1}"
if [[ $JAVA_VERSION == 1.8* ]]; then
    JRE="jre/"
elif [[ $JAVA_VERSION == 11* ]]; then
    JRE=""
else
    echo "Unkown java version: $JAVA_VERSION"
    exit 1
fi
readonly COMPONENT_DIR="component_temp_dir"
readonly LANGUAGE_PATH="$COMPONENT_DIR/$JRE/languages/nabla"

rm -rf COMPONENT_DIR

mkdir -p "$LANGUAGE_PATH"
cp target/nabla.jar "$LANGUAGE_PATH"

mkdir -p "$LANGUAGE_PATH/bin"
cp nabla $LANGUAGE_PATH/bin/

touch "$LANGUAGE_PATH/native-image.properties"

mkdir -p "$COMPONENT_DIR/META-INF"
{
    echo "Bundle-Name: Nabla";
    echo "Bundle-Symbolic-Name: fr.cea.nabla";
    echo "Bundle-Version: 20.0.0";
    echo 'Bundle-RequireCapability: org.graalvm; filter:="(&(graalvm_version=20.0.0)(os_arch=amd64))"';
    echo "x-GraalVM-Polyglot-Part: True"
} > "$COMPONENT_DIR/META-INF/MANIFEST.MF"

(
cd $COMPONENT_DIR || exit 1
jar cfm ../nabla-component.jar META-INF/MANIFEST.MF .

echo "bin/nabla = ../$JRE/languages/nabla/bin/nabla" > META-INF/symlinks
jar uf ../nabla-component.jar META-INF/symlinks

{
    echo "$JRE"'languages/nabla/bin/nabla = rwxrwxr-x'
} > META-INF/permissions
jar uf ../nabla-component.jar META-INF/permissions
)
rm -rf $COMPONENT_DIR
