<?php

namespace Views;

class BackgroundExtension extends \Twig_Extension {
	private $backgrounds = [
        'yun_1375.jpg',
        'yun_7604.jpg',
        'yun_3218.jpg',
        'yun_1077.jpg',
        'yun_3281.jpg',
	];

    public function __construct()
    {
    }

    public function getName()
    {
        return 'background';
    }

    public function getGlobals()
    {
        $index = array_rand($this->backgrounds);
        return [
            'background' => $this->backgrounds[$index],
        ];
    }
}
