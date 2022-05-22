package main

import (
	"github.com/flosch/pongo2/v5"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"github.com/labstack/gommon/log"
	// "github.com/imdario/mergo"
	// "github.com/nicksnyder/go-i18n/v2/i18n"
	// "io"
	"net/http"
)

func main() {
	e := echo.New()

	// Debug
	debug := profileEnv == EnvDevelopment
	e.Debug = debug

	if debug {
		e.Logger.SetLevel(log.DEBUG)
	} else {
		e.Logger.SetLevel(log.INFO)
	}

	// Template
	t := NewTemplate(e.Logger)
	e.Renderer = t

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
			// "title": "foo",
		})
}

func Healthcheck(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "private, no-cache, no-store, must-revalidate")
	return c.String(http.StatusOK, "OK")
}
