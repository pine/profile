_ = require 'lodash'
isWin = /^win/.test process.platform

module.exports = (grunt) ->
  grunt.initConfig
    csslint:
      options:
        csslintrc: '.csslintrc'
        import: false
      files: [
        'public/**/*.css'
        '!public/css/html5reset.css'
      ]

    phplint:
      options:
        limit: 10
        phpCmd: 'php'
        stdout: true
        stderr: true
      files: [
        '**/*.php'
        '!vendor/**/*'
        '!node_modules/**/*'
      ]

    coffeelint:
      files: [
        '*.coffee'
      ]

    htmlhint:
      options:
        htmlhintrc: '.htmlhintrc'
      files: [
        'views/**/*.html'
      ]

    jsonlint:
      files: [
        '**/*.json'
        '!vendor/**/*'
        '!node_modules/**/*'
      ]

    bashlint:
      ci: []

    jshint:
      options:
        jshintrc: true

      files: [
      ]

    concurrent:
      options:
        logConcurrentOutput: false
      lint: _.union([
        'phplint'
        'csslint'
        'coffeelint'
        'htmlhint'
        'jsonlint'
        'jshint'
      ], !isWin && ['bashlint'])

  grunt.registerTask 'lint', 'concurrent:lint'
  grunt.registerTask 'test', ['lint']
  grunt.registerTask 'default', []

  require('load-grunt-tasks')(grunt)
  require('phplint').gruntPlugin(grunt)
