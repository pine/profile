<?php

namespace Views;

class LastModifiedExtension extends \Twig_Extension {
    public function __construct()
    {
    }

    public function getName()
    {
        return 'last_modified';
    }

    public function getFunctions()
    {
        return [
            new \Twig_SimpleFunction('last_modified_for', array($this, 'lastModifiedFor')),
        ];
    }

    public function lastModifiedFor($name) {
        $work_tree = realpath(__DIR__.'/../../');
        $view_path = realpath("$work_tree/views/$name.html");
        $git_cmd   = "git log -1 --pretty=format:\"%ad\" --date=rfc $view_path";

        if (file_exists($view_path)) {
            $result = exec("cd $work_tree && $git_cmd");
		    return strtotime($result);
        }
    }
}
