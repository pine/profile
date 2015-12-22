<?php

namespace Views;

class MenuExtension extends \Twig_Extension {
    private $router;
    private $request;

    public function __construct($router, $request, $menus)
    {
        $this->router  = $router;
        $this->request = $request;
        $this->menus   = $menus;
    }

    public function getName()
    {
        return 'menu';
    }

    public function getGlobals()
    {
        $name = $this->getRouteName();
        return [
            'name'  => $name,
            'menus' => $this->getMenus($name),
        ];
    }

    private function getRouteName() {
        $routeInfo = $this->router->dispatch($this->request);

        if ($routeInfo[0] === \FastRoute\Dispatcher::FOUND) {
            $route = $this->router->lookupRoute($routeInfo[1]);
            return $route->getName();
        }
    }

    private function getMenus($name) {
        foreach ($this->menus as &$menu) {
            $menu['active'] = $menu['name'] === $name;
        }

        return $this->menus;
    }
}
