#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls -ld "$PRG"
    link=`expr "$PRG" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

# Use the maximum available, or set MAX_FD != maximum.
MAX_FD="maximum"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

# For Cygwin or MSYS, switch paths to Windows format before running java
if [ "$cygwin" = "true" -o "$msys" = "true" ] ; then
    APP_HOME=`cygpath --path --mixed "$APP_HOME"`
    CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`
    JAVACMD=`cygpath --url "$JAVACMD"`

    # We build the pattern for arguments to be converted via cygpath
    ROOTDIRSRAW=`find -L / -maxdepth 3 -type d -name gradle 2>/dev/null | head -1`
    SEP=""
    for dir in $ROOTDIRSRAW ; do
        ROOTDIR="$dir"
        SEP=":"
    done
    OURCYGPATTERN="(^($ROOTDIR))"
    OURCYGPATTERN="$OURCYGPATTERN|(^)"
    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    i=0
    for arg in "$@" ; do
        CHECK=`echo "$arg"|egrep -c "$OURCYGPATTERN" -`
        CHECK2=`echo "$arg"|egrep -c "^-"`                                 ### Determine if an option

        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a path
            arg=`cygpath --path --unix "$arg"`
        fi
        i=$((i+1))
        eval "set -- \"\$@\" '$arg'"
    done
    case $i in
        (0) set -- ;;
        (1) set -- "$1" ;;
        (2) set -- "$1" "$2" ;;
        (3) set -- "$1" "$2" "$3" ;;
        (4) set -- "$1" "$2" "$3" "$4" ;;
        (5) set -- "$1" "$2" "$3" "$4" "$5" ;;
        (6) set -- "$1" "$2" "$3" "$4" "$5" "$6" ;;
        (7) set -- "$1" "$2" "$3" "$4" "$5" "$6" "$7" ;;
        (8) set -- "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8" ;;
        (9) set -- "$1" "$2" "$3" "$4" "$5" "$6" "$7" "$8" "$9" ;;
    esac
fi

# Escape application args
save () {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}
APP_ARGS=$(save "$@")

# Collect all arguments for the java command, following the shell quoting and substitution rules
eval "set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \"\-classpath\" \"\$APP_HOME/gradle/wrapper/gradle-wrapper.jar\" org.gradle.wrapper.GradleWrapperMain \"$APP_ARGS\""

# by default we should be in the correct project dir, but when run from Finder on Mac, the cwd is wrong
if [ "$(uname)" = "Darwin" ] && [ "$HOME" = "$PWD" ]; then
  cd "$(dirname "$0")"
fi

exec "$JAVACMD" "$@"
