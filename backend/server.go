package main

import (
	"github.com/flosch/pongo2/v5"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"github.com/labstack/gommon/log"
	"github.com/imdario/mergo"
	// "github.com/nicksnyder/go-i18n/v2/i18n"
	"io"
	"net/http"
	"os"
)

type Renderer struct {
	templates *pongo2.TemplateSet
}

func (r *Renderer) Render(w io.Writer, name string, data interface{}, c echo.Context) error {
	t, err := r.templates.FromCache(name)
	if err != nil {
		return err
	}

	pc := pongo2.Context{
		"path": c.Path(),
		"site_url": "",
	}
	if data != nil {
		mergo.Merge(&pc, data.(pongo2.Context))
		return t.ExecuteWriter(pc, w)
	}

	return t.ExecuteWriter(pc, w)
}

func main() {
	e := echo.New()

	// Debug
	env := os.Getenv("PF_ENV")
	if env == "" {
		env = "development"
	}

	debug := env == "development"
	e.Debug = debug

	if debug {
		e.Logger.SetLevel(log.DEBUG)
	} else {
		e.Logger.SetLevel(log.INFO)
	}
	e.Logger.Infof("Environment detected. (env=%v, debug=%v)", env, debug)

	// Template
	t := pongo2.NewSet("main", pongo2.MustNewLocalFileSystemLoader("templates"))
	t.Debug = debug

	r := &Renderer{templates: t}
	e.Renderer = r

	e.Use(middleware.Secure())
	e.Use(middleware.Logger())

	e.GET("/", Index)
	e.GET("/home/ja", HomeJa)
	e.GET("/healthcheck", Healthcheck)

	e.Logger.Fatal(e.Start(":1323"))
}

func Index(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "public, max-age=180, must-revalidate")
	return c.Redirect(http.StatusSeeOther, "/home/ja")
}

func HomeJa(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "public, max-age=180, must-revalidate")
	return c.Render(http.StatusOK, "home.html",
		pongo2.Context{
			"title": "foo",
		})
}

func Healthcheck(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "private, no-cache, no-store, must-revalidate")
	return c.String(http.StatusOK, "OK")
}
