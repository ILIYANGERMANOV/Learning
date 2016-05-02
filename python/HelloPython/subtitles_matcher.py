#!/usr/bin/python
import sys
from helpers.extractor import *
from helpers.subs_matcher import SubsMatcher


def validate_input(argv):
    # Validating arguments count
    if len(argv) < 2:
        raise ValueError("Wrong number of arguments passed")

    # Validating series path
    series_path = argv[0]
    if not os.path.isdir(series_path):
        raise ValueError("Series path '%s' must be directory." % series_path)

    # Validating subtitles path
    subtitles_path = argv[1]
    if not os.path.exists(subtitles_path):
        raise ValueError("Subtitles path '%s' should exist." % subtitles_path)

    print("Input validated successfully.")
    return [series_path, subtitles_path]


def obtain_subs_dir_path(subtitles_path):
    if os.path.isdir(subtitles_path):
        # Subtitles are unzipped, return path
        return subtitles_path

    # Subtitles' path is a file, try extract them
    extract_dir_path = os.path.join(os.getcwd(), "tmp")
    extractor = Extractor(subtitles_path)
    extractor.extract_to_dir(extract_dir_path)
    return extract_dir_path


def main():
    try:
        validated_paths = validate_input(sys.argv[1::])
        subs_dir_path = obtain_subs_dir_path(validated_paths[1])
        subs_matcher = SubsMatcher(validated_paths[0], subs_dir_path)
        subs_matcher.match_subs()
    except (ValueError, NotSupportedExtensionError) as err:
        print("Error: " + str(err))
        if isinstance(err, NotSupportedExtensionError):
            print("Manually extract subtitles and run the scrip again.")
        print("Terminating script...")


if __name__ == '__main__':
    main()
