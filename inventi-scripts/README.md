# Inventi Scripts
All the scripts are written in cloujure script.

## Development
1. install NPM modules (needed only once) ``npm install`` 
2. start REPL with ``lein figwheel``. Note for OS X users: i recommend to use ``rlwrap`` for better repl experience. You have to install it first, with package manager, like ``brew install rlwrap`` and then run it like this ``rlwrap lein figwheel``.
3. After repl is started connect it to node js - ``node target/server_dev/inventi_scripts.js``

You are ready to code!

Typically you have to create your own namespace and put the code there.
Also do not forget to register your command in hubot/init-scripts it shoud bind to function wich takes hubot response object as a parameter.

## Tesing hubot locally
If you want to see how your script behave in hubot, do the following:

1. Compile prod code - ``lein cljsbuild once prod``. This should create ``app/index.js`` file.
2. Once build is done, run the hubot, from the main dir ``./bin/hubot ``
3. Enter commands like you would do in chat e.g. ``hubot do something``

## Prod build
Before releasing your code make sure you have tested it on local hubot.

1. Add command insturctions to ``src/help.js``.
2. Commit code to the git
3. Compile prod code - ``lein cljsbuild once prod``
4. Commit prod code to the git
5. Push code to the repo.
6. Do pull request if you have no commit access.

After the push hubot will automatically deploy to heroku.


