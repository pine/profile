<?php

$app->get('/', function ($request, $response, $args) {
    return $this->view->render($response, 'index.html', []);
})->setName('index');
