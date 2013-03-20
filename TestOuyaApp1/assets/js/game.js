window.Game = (function() {

    var version = '0.1'
      , _clock = null
      , _rate = 10
      , _tick = 0

      , _debug = true
      , _paused = false
      , _controllers = [{}]

      , log = function(msg) {
            if (_debug) {
                console.log(msg);
            }
        }

      , animLoop = function() {
            window.cancelAnimFrame(_clock);
            _tick++;

            if (_tick % _rate == 0) {
                _controllers = pollControllers();
                msgbus.publish('game/tick');
            }

            _clock = window.requestAnimFrame(animLoop);
        }

      , start = function() {
            Game.log('game started');
            _clock = window.requestAnimFrame(animLoop);
        }

      , isPaused = function() {
            return _paused;
        }

      , pause = function() {
            window.cancelAnimFrame(_clock);
            $('#pause-screen').style.display = 'block';
            _paused = true;
        }

      , unpause = function() {
            _clock = window.requestAnimFrame(animLoop);
            $('#pause-screen').style.display = 'none';
            _paused = false;
        }

      , setDebug = function(state) {
            _debug = state;
        }
    ;

    // dumb sprite
    var sx = 0, sy = 0;
    var sdom = document.createElement('div');
    sdom.id = 'sdom';
    sdom.style.position = 'absolute';
    sdom.style.left = sx + 'px';
    sdom.style.top = sy + 'px';

    setTimeout(function() {
        document.body.appendChild(sdom);
    }, 1000);

    return {
        version: version
      , start: start
      , pause: pause
      , unpause: unpause
      , isPaused: isPaused
      , setDebug: setDebug
      , log: log
    }
})();

msgbus.subscribe('game/tick', function(topic) {
    // Game logic
});

msgbus.subscribe('hardware/controller', function(topic, cData) {
    if (cData) {
        if (cData.y == 1) {
            if (Game.isPaused()) {
                Game.unpause();
            } else {
                Game.pause();
            }
        }

        if (cData.u == 1) {
            var sdom = $('#sdom');
            if (sdom) {
                var sx = parseInt(sdom.style.left)
                  , sy = parseInt(sdom.style.top)
                ;

                if (sx > 64) {
                    sdom.style.left = (sx - 64) + 'px';
                }
            }
        }

        if (cData.a == 1) {
            var sdom = $('#sdom');
            if (sdom) {
                var sx = parseInt(sdom.style.left)
                  , sy = parseInt(sdom.style.top)
                ;

                if (sx < 640) {
                    sdom.style.left = (sx + 64) + 'px';
                }
            }
        }

        updateDebugPanels(cData);
    }
});

function updateDebugPanels(cData) {
    // controller debug panels
    var domRoot = $('#controller' + (cData.player + 1));
    if (domRoot) {
        var fieldRoot = '#c' + (cData.player +1)
          , stickThreshold = 0.2
          , triggerThreshold = 0.2
          , lsx1 = (cData.ls.x > stickThreshold) ? parseFloat(cData.ls.x).toFixed(2) : 0
          , lsy1 = (cData.ls.y > stickThreshold) ? parseFloat(cData.ls.y).toFixed(2) : 0
          , rsx1 = (cData.rs.x > stickThreshold) ? parseFloat(cData.rs.x).toFixed(2) : 0
          , rsy1 = (cData.rs.y > stickThreshold) ? parseFloat(cData.rs.y).toFixed(2) : 0
          , l21 = (cData.l2 > triggerThreshold) ? parseFloat(cData.l2).toFixed(2) : 0
          , r21 = (cData.r2 > triggerThreshold) ? parseFloat(cData.r2).toFixed(2) : 0
        ;

        lsx1 = (cData.ls.x < stickThreshold*-1) ? parseFloat(cData.ls.x).toFixed(2) : lsx1;
        lsy1 = (cData.ls.y < stickThreshold*-1) ? parseFloat(cData.ls.y).toFixed(2) : lsy1;
        rsx1 = (cData.rs.x < stickThreshold*-1) ? parseFloat(cData.rs.x).toFixed(2) : rsx1;
        rsy1 = (cData.rs.y < stickThreshold*-1) ? parseFloat(cData.rs.y).toFixed(2) : rsy1;

        $(fieldRoot + '_o').value = cData.o;
        $(fieldRoot + '_u').value = cData.u;
        $(fieldRoot + '_y').value = cData.y;
        $(fieldRoot + '_a').value = cData.a;

        $(fieldRoot + '_lsx').value = lsx1;
        $(fieldRoot + '_lsy').value = lsy1;
        $(fieldRoot + '_rsx').value = rsx1;
        $(fieldRoot + '_rsy').value = rsy1;
        $(fieldRoot + '_l2').value = l21;
        $(fieldRoot + '_r2').value = r21;
    }
}
