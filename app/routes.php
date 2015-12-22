<?php

$routes = [
    [
        'path'  => '/',
        'name'  => 'index',
        'title' => 'トップページ',
    ],
    [
        'path'  => '/program',
        'name'  => 'program',
        'title' => 'プログラミング',
    ],
    [
        'path'  =>  '/anime',
        'name'  => 'anime',
        'title' => 'アニメ',
    ],
    [
        'path'  =>  '/light_novel',
        'name'  => 'light_novel',
        'title' => 'ライトノベル',
    ],
    [
        'path'  =>  '/link',
        'name'  => 'link',
        'title' => 'リンク',
    ],
    [
        'path'  =>  '/site_info',
        'name'  => 'site_info',
        'title' => 'サイト情報',
    ],
];

foreach ($routes as $route) {
    $name = $route['name'];
    $app->get($route['path'], function ($request, $response, $args) use ($name) {
        return $this->view->render($response, $name.'.html');
    })->setName($name);
}
