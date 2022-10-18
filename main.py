# coding: utf-8

import pandas as pd
import yaml

with open('data/mountain.yml') as f:
    mountain_df = pd.DataFrame(yaml.safe_load(f)).set_index('id')

with open('data/climbed_mountain.yml') as f:
    climbed_df = pd.DataFrame(yaml.safe_load(f)).set_index('id')

df = climbed_df.merge(mountain_df, left_on='mountain_id', right_index=True) \
        .sort_index()
print(df)

