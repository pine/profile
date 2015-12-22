<?php

namespace Views;

use FastRoute\Dispatcher;

class MenuExtension extends \Twig_extension {
    private $router;
    private $request;

    private $menus = [
        [
            'name'  => 'index',
            'path'  => '/',
            'title' => 'トップページ',
        ],
    ];

    public function __construct($router, $request)
    {
        $this->router  = $router;
        $this->request = $request;
    }

    public function getName()
    {
        return 'menu';
    }

    public function getGlobals()
    {
        return [
            'menus' => $this->getMenus(),
        ];
    }

    protected function getRouteName() {
        $routeInfo = $this->router->dispatch($this->request);

        if ($routeInfo[0] === Dispatcher::FOUND) {
            $route = $this->router->lookupRoute($routeInfo[1]);
            return $route->getName();
        }
    }

    protected function getMenus() {
        $name = $this->getRouteName();

        foreach ($this->menus as &$menu) {
            $menu['active'] = $menu['name'] === $name;
        }

        return $this->menus;
    }
}
