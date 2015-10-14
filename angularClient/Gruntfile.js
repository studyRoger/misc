module.exports = function(grunt) {


  var dest_base = '../jerseyAngular/src/main/webapp'
  var dest_app = dest_base + '/app'
  var dest_bower = dest_base + '/bower_components'

  var cwd_bin = 'build';

  var src_js_dev = ['src/**/*.js', '!src/properties/*.js', 'src/properties/*_dev.js'];
  var src_js_prod = ['src/**/*.js', '!src/properties/*.js', 'src/properties/*_prod.js'];
  var bin_js = cwd_bin + '/app.min.js';

  var cwd_html = 'src';
  var src_html = ['**/*.html'];
  var bin_html = cwd_bin;


  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    uglify: {
      options: {
        banner: '/*! <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      dev: {
        options: {
          beautify: true
        },
        src: src_js_dev,
        dest: bin_js
      },
      prod: {
        src: src_js_prod,
        dest: bin_js
      }
    },

    htmlmin: {
      dev: {

        expand: true,
        cwd: cwd_html,
        src: src_html,
        dest: bin_html
      },
      prod: {
        options: {
          removeComments: true,
          collapseWhitespace: true
        },
        expand: true,
        cwd: cwd_html,
        src: src_html,
        dest: bin_html
      }
    },

    bowerInstall: {
      target: {
        src: ['src/index.html']
      }
    },

    /*wiredep: {
      task: {
        src: ['src/index.html']
      }
    },*/

    copy: {
      main: {
        files: [{
          expand: true,
          cwd: 'build',
          src: ['**/*'],
          dest: dest_app,
          filter: 'isFile'
        }],
      },
      bower: {
        files: [{
          expand: true,
          cwd: 'bower_components',
          src: ['**/*'],
          dest: dest_bower,
          filter: 'isFile'
        }],
      }
    },

    clean: [cwd_bin],

    watch: {
      js: {
        files: src_js_dev,
        tasks: ['uglify:dev']
      },
      html: {
        files: ['src/**/*.html'],
        tasks: ['htmlmin:dev']
      },
      bower: {
        files: ['bower.json'],
        tasks: ['bowerInstall', 'copy:bower']
      },
      build: {
        files: ['build/**/*'],
        tasks: ['copy:main']
      }
    }

  });

  // Default task(s).
  grunt.registerTask('build_dev', ['uglify:dev', 'bowerInstall', 'htmlmin:dev']);
  grunt.registerTask('build_prod', ['uglify:prod', 'bowerInstall', 'htmlmin:prod']);
  grunt.registerTask('dist_dev', ['build_dev', 'copy'])
  grunt.registerTask('dist_prod', ['build_prod', 'copy'])

  grunt.registerTask('default', ['build_prod']);

  // Load the plugins
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-htmlmin');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-copy');
  //grunt.loadNpmTasks('grunt-wiredep');
  grunt.loadNpmTasks('grunt-bower-install');

};