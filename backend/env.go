package main

import (
	"os"
)

const (
	EnvProduction  = "production"
	EnvDevelopment = "development"
)

var (
	profileEnv     string
	profileSiteUrl string
)

func init() {
	profileEnv = getenv("PROFILE_ENV", "development")
	profileSiteUrl = getenv("PROFILE_SITE_URL", "")
}

func getenv(name string, defval string) string {
	v := os.Getenv(name)
	if v == "" {
		return defval
	}
	return v
}
