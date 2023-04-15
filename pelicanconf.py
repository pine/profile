import os
import sys

sys.path.append('.')


#
# Master data
#
from data.preprocessed import MOUNTAINS


#
# Custom settings
#
ENV = os.getenv('PYTHON_ENV', 'development')
NOINDEX = ENV == 'development'


#
# Basic settings
#
SITENAME = '水音氷音 (ぴね) プロフィール'

if ENV == 'production':
    SITEURL = 'https://blog.emoji-gen.ninja'
else:
    SITEURL = ''


#
# URL settings
#
ARTICLE_URL = 'posts/{date:%Y%m%d}/{slug}.html'
ARTICLE_SAVE_AS = 'posts/{date:%Y%m%d}/{slug}.html'
CATEGORY_SAVE_AS = ''
AUTHOR_SAVE_AS = ''
ARCHIVES_SAVE_AS = ''
AUTHORS_SAVE_AS = ''
CATEGORIES_SAVE_AS = ''
TAGS_SAVE_AS = ''


#
# Time and Date
#
TIMEZONE = 'Asia/Tokyo'
DEFAULT_DATE_FORMAT = '%Y/%m/%d'


#
# Feed settings
#
FEED_ATOM = None
FEED_ALL_ATOM = None
FEED_ALL_RSS = None
CATEGORY_FEED_ATOM = None
TRANSLATION_FEED_ATOM = None
AUTHOR_FEED_ATOM = None
AUTHOR_FEED_RSS = None


#
# Translations
#
DEFAULT_LANG = 'ja'


#
# Themes
#
THEME = 'theme'
