#!/bin/bash

page_id=$1

if [[ ! ($page_id =~ ^[a-z0-9_-]+$) ]]; then
    echo null
    exit 1
fi

filename="$1.inc"

cd contents

if [ -e $filename ]; then
    git log -1 --pretty=format:"%ad" --date=rfc $filename
    exit 0
else
    echo null
    exit 1
fi
