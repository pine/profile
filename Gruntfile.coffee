_ = require 'lodash'
isWin = /^win/.test process.platform

module.exports = (grunt) ->
  grunt.initConfig
    csslint:
      options:
        csslintrc: '.csslintrc'
        import: false
      files: [
        'site/index*.css'
        'site/contents/*.css'
      ]
    
    phplint:
      options:
        limit: 10
        phpCmd: 'php'
        stdout: true
        stderr: true
      files: [
        'site/**/*.php'
        'site/**/*.inc'
      ]
    
    coffeelint:
      files: ['*.coffee']
    
    htmlhint:
      options:
        htmlhintrc: '.htmlhintrc'
      files: [
        'site/index.inc'
        'site/contents/*.inc'
      ]
    
    jsonlint:
      files: ['*.json', 'site/**/*.json']
    
    bashlint:
      ci: ['.ci/**/*.sh']
      site: ['site/**/*.sh']
      
    jshint:
      options:
        jshintrc: true
      
      files: [
        'site/**/*.js'
        '!site/html5.js'
        '!site/ie7-squish.js'
        '!site/IE8.js'
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