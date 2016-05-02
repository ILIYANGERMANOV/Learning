import os
import zipfile
import patoolib


class NotSupportedExtensionError(Exception):
    pass


class Extractor:
    supported_extensions = ()  # TODO enable extract support

    def __init__(self, path_to_archive_file):
        """

        :param path_to_archive_file: path to the file that will be extracted
        """
        self.archive_path = os.path.abspath(path_to_archive_file)
        self.extension = path_to_archive_file.split(".")[-1]

    def extract_to_dir(self, dst_dir_name):
        """
        Raises NotSupportedExtensionError
        :param dst_dir_name: The name of the destination directory, which will be created in CWD.
        :return: None
        """
        if self.extension not in Extractor.supported_extensions:
            # Extractor does not support current extension, raise error
            raise NotSupportedExtensionError("Extractor: '%s' is not supported." % self.extension)

        dst_dir_path = os.path.join(os.getcwd(), dst_dir_name)
        if not os.path.exists(dst_dir_path):
            # Destination directory does not exists, create it
            os.mkdir(dst_dir_path)

        if self.extension == "rar":
            self.__extractor__extract_rar(dst_dir_path)
        elif self.extension == "zip":
            self.__extractor__extract_zip(dst_dir_path)

    def __extractor__extract_rar(self, dst_dir_path):
        patoolib.extract_archive(self.archive_path, outdir=dst_dir_path)

    def __extractor__extract_zip(self, dst_dir_path):
        with open(self.archive_path) as archive_file:
            zipfile.ZipFile(archive_file).extractall(path=dst_dir_path)
