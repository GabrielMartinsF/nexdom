import { defineConfig } from '#q-app/wrappers'

export default defineConfig((/* ctx */) => {
  return {
    boot: [
      'axios',
    ],
    css: [
      'app.scss'
    ],

    extras: [
      'roboto-font', // optional, you are not bound to it
      'material-icons', // optional, you are not bound to it
    ],

    // Full list of options: https://v2.quasar.dev/quasar-cli-vite/quasar-config-file#build
    build: {
      target: {
        browser: [ 'es2022', 'firefox115', 'chrome115', 'safari14' ],
        node: 'node20'
      },

      vueRouterMode: 'hash', // available values: 'hash', 'history'
    },
    devServer: {
      open: true
    },
    framework: {
      lang: "pt-BR",
      config: {},
      plugins: ["Notify"]
    },
    animations: [],

    ssr: {
      prodPort: 3000, 

      middlewares: [
        'render'
      ],

      pwa: false
      
    },

    pwa: {
      workboxMode: 'GenerateSW' 
    },

    
    cordova: {
      
    },

    
    capacitor: {
      hideSplashscreen: true
    },

    
    electron: {
      
      preloadScripts: [ 'electron-preload' ],

      inspectPort: 5858,

      bundler: 'packager', 

      packager: {
        
      },

      builder: {
        appId: 'nexdom-frontend'
      }
    },

    bex: {
      extraScripts: []
    }
  }
})
