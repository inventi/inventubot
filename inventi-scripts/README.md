# CLJS scripts dev guide
This guide explains how to write hubot scipts in Clojure Scirpt.

## Development
This is the list of things you have to do to get started hacking the scripts.

1. install NPM modules (needed only once) ``npm install`` 
2. start REPL with ``lein figwheel``. Note for OS X users: i recommend to use ``rlwrap`` for better repl experience. You have to install it first, with package manager, like ``brew install rlwrap`` and then run it like this ``rlwrap lein figwheel``.
3. After repl is started connect it to node js - ``node target/server_dev/inventi_scripts.js``

You are ready to code!
Typically you have to create your own namespace and put the code there. Then bind your command regexp to function in hubot/init-scripts. See other script sources for example how to do that.

## Testing hubot locally
If you want to see how your script behave in hubot, compile it and start your own bot:

1. Clean generated sources - ``lein clean``
2. Compile with prod option - ``lein cljsbuild once prod``. This should create ``app/index.js`` file.
3. Once build is done, run the hubot, from the main dir ``./bin/hubot ``
4. Enter commands like you would do in chat e.g. ``hubot do something``

## Prod build
Before releasing your code make sure you have tested it on local hubot.

1. Add command insturctions to ``src/help.js``.
2. Commit code to the git
3. Clean generated sources - ``lein clean``
4. Compile prod code - ``lein cljsbuild once prod``
5. Commit prod code to the git
6. Push code to the repo.
7. Do pull request if you have no commit access.

After the push hubot will automatically deploy to heroku.


