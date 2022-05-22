package main

import (
	"github.com/flosch/pongo2/v5"
	"github.com/labstack/echo/v4"
	// "github.com/labstack/echo/v4/middleware"
	"github.com/imdario/mergo"
	// "github.com/labstack/gommon/log"
	// "github.com/nicksnyder/go-i18n/v2/i18n"
	"io"
	// "net/http"
	// "os"
	// "fmt"
)

type (
	Template struct {
		views  *pongo2.TemplateSet
		logger echo.Logger
	}
)

func NewTemplate(logger echo.Logger) *Template {
	v := pongo2.NewSet("main", pongo2.MustNewLocalFileSystemLoader("templates"))
	v.Debug = profileEnv == EnvDevelopment

	t := &Template{
		views:  v,
		logger: logger,
	}

	return t
}

func (t *Template) Render(w io.Writer, name string, data interface{}, c echo.Context) error {
	v, err := t.views.FromCache(name)
	if err != nil {
		return err
	}

	pc := pongo2.Context{
		"path":     c.Path(),
		"site_url": profileSiteUrl,
	}
	if data != nil {
		mergo.Merge(&pc, data.(pongo2.Context))
	}

	t.logger.Infof("Apply template. ctx: %v", pc)
	return v.ExecuteWriter(pc, w)
}
