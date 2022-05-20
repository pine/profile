package main

import (
	"github.com/flosch/pongo2/v5"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"io"
	"net/http"
)

type Renderer struct {
	templates *pongo2.TemplateSet
}

func (r *Renderer) Render(w io.Writer, name string, data interface{}, c echo.Context) error {
	t, err := r.templates.FromCache(name)
	if err != nil {
		return err
	}

	return t.ExecuteWriter(nil, w)
}

func main() {
	e := echo.New()
	e.Use(middleware.Secure())
	e.Use(middleware.Logger())

	r := &Renderer{
		templates: pongo2.NewSet("main", pongo2.MustNewLocalFileSystemLoader("templates")),
	}
	e.Renderer = r

	e.GET("/", Index)
	e.GET("/home/ja", HomeJa)
	e.GET("/healthcheck", Healthcheck)

	e.Logger.Fatal(e.Start(":1323"))
}

func Index(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "public, max-age=180, must-revalidate")
	return c.Redirect(http.StatusSeeOther, "/ja/home")
}

func HomeJa(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "public, max-age=180, must-revalidate")
	return c.Render(http.StatusOK, "home.html", nil)
}

func Healthcheck(c echo.Context) error {
	c.Response().Header().Set("Cache-Control", "private, no-cache, no-store, must-revalidate")
	return c.String(http.StatusOK, "OK")
}
