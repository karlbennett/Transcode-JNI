##################
# Setup the directories #
##################

# The target directory of the maven build, all the compiled code will be placed under this directory.
TARGET = ../../../target/

# The directory that will contain the compiled java classes.
CLASSES_DIR = $(TARGET)classes/

# The directory that will contain the generated JNI header files.
JNI_DIR = $(TARGET)jni/

# The directory that will contain the compiled native files.
LIB_DIR = $(CLASSES_DIR)lib/


#################
# Setup JNI compiling #
#################

# The javah binary.
JAVAH = $(JAVA_HOME)bin/javah

# The package for the java JNI test classes.
JAVA_PACKAGE = org.karlbennett.transcode.jni

# The first java JNI test class.
JAVA_CLASSES = $(JAVA_PACKAGE).JniTranscodeInputStream

# The command that will generate the JNI header files from the java JNI test classes.
JAVAH_CMD = $(JAVAH) -classpath $(CLASSES_DIR) -d $(JNI_DIR) $(JAVA_CLASSES)


# The build.
all :
	$(JAVAH_CMD) # First generate the JNI header files because these are required to compile the C++ source files.

clean :
	rm -f $(JNI_DIR)*.h
