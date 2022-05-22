package main

import (
	"github.com/flosch/pongo2/v5"
	"github.com/labstack/echo/v4"
	// "github.com/labstack/echo/v4/middleware"
	// "github.com/labstack/gommon/log"
	"github.com/imdario/mergo"
	// "github.com/nicksnyder/go-i18n/v2/i18n"
	"io"
	// "net/http"
	// "os"
	// "fmt"
)

type Template struct {
	views   *pongo2.TemplateSet
	siteUrl string
}

func NewTemplate() *Template {
	v := pongo2.NewSet("main", pongo2.MustNewLocalFileSystemLoader("templates"))
	v.Debug = profileEnv == EnvDevelopment

	return &Template{views: v}
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
		return v.ExecuteWriter(pc, w)
	}

	return v.ExecuteWriter(pc, w)
}
