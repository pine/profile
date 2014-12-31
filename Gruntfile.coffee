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
    
    concurrent:
      options:
        logConcurrentOutput: false
        limit: 2
      lint: [
        'phplint'
        'csslint'
        'coffeelint'
        'htmlhint'
        'jsonlint'
      ]
  
  grunt.registerTask 'lint', 'concurrent:lint'
  grunt.registerTask 'test', ['lint']
  grunt.registerTask 'default', []
  
  require('load-grunt-tasks')(grunt)
  require('phplint').gruntPlugin(grunt)