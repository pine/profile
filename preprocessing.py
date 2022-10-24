# coding: utf-8

import pandas as pd
import yaml

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

df = climbed_df.merge(mountain_df) \
        .merge(mountain_cordillera_df, how='left') \
        .merge(cordillera_df, how='left') \
        .set_index('climbed_mountain_id') \
        .sort_index()

print(df.to_string())
# print(df.to_dict('records'))
print(df.index.dtype)
print(df.dtypes)
