import os
import shutil


def get_name_without_extension(file_name):
    return ''.join(file_name.split(".")[0:-1])


class SubsMatcher:
    supported_movie_ext = ("avi", "mp4")
    supported_subs_ext = ("srt", "sub")

    def __init__(self, series_path, subs_path):
        self.series_path = series_path
        self.subs_path = subs_path

    def copy_file(self, sub):
        sub_path = os.path.join(self.series_path, sub.full_file_name)
        try:
            shutil.copy(sub_path, self.series_path)
        except shutil.SameFileError as err:
            print("Job already done: " + str(err))

    def rename_and_copy_file(self, sub):
        # TODO rename sub file
        self.copy_file(sub)

    def log_match_fails(self, sub):
        pass

    def match_subs(self):
        series_list = [EpisodeEntry(file_name) for file_name in os.listdir(self.series_path)
                       if file_name.endswith(SubsMatcher.supported_movie_ext)]
        subs_list = [SubEntry(file_name) for file_name in os.listdir(self.subs_path)
                     if file_name.endswith(SubsMatcher.supported_subs_ext)]

        match_flag_executor = {
            EpisodeEntry.COMPLETE_MATCH: self.copy_file,
            EpisodeEntry.SURE_MATCH: self.rename_and_copy_file,
            EpisodeEntry.NOT_MATCH: self.log_match_fails
        }

        for episode in series_list:
            match_flag, sub = episode.match(subs_list)
            match_flag_executor[match_flag](sub)


class EpisodeEntry:
    COMPLETE_MATCH = 1
    SURE_MATCH = 2
    NOT_MATCH = 3

    def __init__(self, full_file_name):
        self.full_file_name = full_file_name
        self.name = get_name_without_extension(full_file_name)

    def match(self, subs_list):
        for sub in subs_list:
            if self.name == sub.name:
                print("Match 100%% for '%s'. Proceed to moving..." % self.name)
                return EpisodeEntry.COMPLETE_MATCH, sub
                # TODO handle not sure and none matches


class SubEntry:
    def __init__(self, full_file_name):
        self.full_file_name = full_file_name
        self.name = get_name_without_extension(full_file_name)
