module.exports = (grunt) ->
  grunt.initConfig
    csslint:
      options:
        import: false
      files: ['site/index.css']
    
    jshint:
      files: []
    
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
    
    concurrent:
      options:
        logConcurrentOutput: false
      lint: ['phplint']
  
  grunt.registerTask 'lint', 'concurrent:lint'
  grunt.registerTask 'test', ['lint']
  grunt.registerTask 'default', []
  
  require('load-grunt-tasks')(grunt)
  require('phplint').gruntPlugin(grunt)