# coding: utf-8

import pandas as pd
import textwrap
import yaml

from jinja2 import Template


with open('data/mountain.yml') as f:
    mountain_df = pd.DataFrame(yaml.safe_load(f)) \
            .astype({'id': 'Int64',
                     'name_ja': 'string',
                     'name_en': 'string',
                     'elevation': 'Int64'}) \
            .rename(columns={'id': 'mountain_id',
                             'name_ja': 'mountain_name_ja',
                             'name_en': 'mountain_name_en'})

with open('data/climbed_mountain.yml') as f:
    climbed_df = pd.DataFrame(yaml.safe_load(f)) \
            .astype({'id': 'Int64',
                     'mountain_id': 'Int64',
                     'climbed_on': 'string'}) \
            .rename(columns={'id': 'climbed_mountain_id'})

with open('data/cordillera.yml') as f:
    cordillera_df = pd.DataFrame(yaml.safe_load(f)) \
            .astype({'id': 'Int64',
                     'name_ja': 'string',
                     'name_en': 'string'}) \
            .rename(columns={'id': 'cordillera_id',
                             'name_ja': 'cordillera_name_ja',
                             'name_en': 'cordillera_name_en'})

with open('data/mountain_cordillera.yml') as f:
    mountain_cordillera_df = pd.DataFrame(yaml.safe_load(f)) \
            .astype('Int64')


mountain_summary_df = climbed_df.merge(mountain_df) \
        .merge(mountain_cordillera_df, how='left') \
        .merge(cordillera_df, how='left') \
        .set_index('climbed_mountain_id') \
        .sort_index() \
        .replace({ pd.NA: None})


source = '''\
    # coding: utf-8

    from typing import NamedTuple

    class Mountain(NamedTuple):
        mountain_id: int
        climbed_on: str
        mountain_name_ja: str
        mountain_name_en: str
        elevation: int
        cordillera_id: int
        cordillera_name_ja: str
        cordillera_name_en: str

    MOUNTAINS = [
        {%- for it in mountains %}
        Mountain({{ it.mountain_id }},
                 "{{ it.climbed_on }}",
                 "{{ it.mountain_name_ja }}",
                 "{{ it.mountain_name_en }}",
                 {{ it.elevation }},
                 {{ it.cordillera_id }},
                 {% if it.cordillera_name_ja %}"{{ it.cordillera_name_ja }}"{% else %}None{% endif %},
                 {% if it.cordillera_name_en %}"{{ it.cordillera_name_en }}"{% else %}None{% endif %}),
        {%- endfor %}
    ]

    print(MOUNTAINS)
'''
template = Template(textwrap.dedent(source))

print(template.render(mountains=mountain_summary_df.to_dict('records')))
