package moe.pine.profile.models;

import lombok.Value;

import java.util.List;

@Value
public class ViewAnimeGroup {
    private String name;
    private List<ViewAnime> items;
}
