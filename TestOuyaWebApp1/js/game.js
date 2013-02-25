window.Game = (function() {

    var version = '0.1'
      , _clock = null
      , _tick = 0
      , _debug = true
      , sprites = []

      , log = function(msg) {
            if (_debug) {
                console.log(msg);
            }
        }

      , addSprite = function() {
            Game.log('addSprite called');

            var sprite = new Sprite({
                src: {
                    x: 0
                  , y: 0
                  , w: 64
                  , h: 64
                  , url: 'sprites/sprite1.png'
                }
              , x: 0
              , y: 0
              , xs: 0
              , ys: 0 
            });

            sprites.push(sprite);
            return sprite;
        }

      , animLoop = function() {
            window.cancelAnimFrame(_clock);
            _tick++;

            var rate = 5;
            if (_tick % rate == 0) {
                try {
                    var ctlr1 = {
                        o: xouya.getO(1)
                      , u: xouya.getU(1)
                      , y: xouya.getY(1)
                      , a: xouya.getA(1)
                      , lsx: xouya.getLS_X(1)
                      , lsy: xouya.getLS_Y(1)
                      , rsx: xouya.getRS_X(1)
                      , rsy: xouya.getRS_Y(1)
                      , l2: xouya.getL2(1)
                      , r2: xouya.getR2(1)
                      , stickThreshold: 0.25
                      , triggerThreshold: 0.8
                    };

                    var lsx1 = (ctlr1.lsx > ctlr1.stickThreshold) ? parseFloat(ctlr1.lsx).toFixed(2) : 0
                      , lsy1 = (ctlr1.lsy > ctlr1.stickThreshold) ? parseFloat(ctlr1.lsy).toFixed(2) : 0
                      , rsx1 = (ctlr1.rsx > ctlr1.stickThreshold) ? parseFloat(ctlr1.rsx).toFixed(2) : 0
                      , rsy1 = (ctlr1.rsy > ctlr1.stickThreshold) ? parseFloat(ctlr1.rsy).toFixed(2) : 0
                      , l21 = (ctlr1.l2 > ctlr1.triggerThreshold) ? parseFloat(ctlr1.l2).toFixed(2) : 0
                      , r21 = (ctlr1.r2 > ctlr1.triggerThreshold) ? parseFloat(ctlr1.r2).toFixed(2) : 0
                    ;

                    lsx1 = (ctlr1.lsx < ctlr1.stickThreshold*-1) ? parseFloat(ctlr1.lsx).toFixed(2) : lsx1;
                    lsy1 = (ctlr1.lsy < ctlr1.stickThreshold*-1) ? parseFloat(ctlr1.lsy).toFixed(2) : lsy1;
                    rsx1 = (ctlr1.rsx < ctlr1.stickThreshold*-1) ? parseFloat(ctlr1.rsx).toFixed(2) : rsx1;
                    rsy1 = (ctlr1.rsy < ctlr1.stickThreshold*-1) ? parseFloat(ctlr1.rsy).toFixed(2) : rsy1;

                    $('#c1_o').value = ctlr1.o;
                    $('#c1_u').value = ctlr1.u;
                    $('#c1_y').value = ctlr1.y;
                    $('#c1_a').value = ctlr1.a;

                    $('#c1_lsx').value = lsx1;
                    $('#c1_lsy').value = lsy1;
                    $('#c1_rsx').value = rsx1;
                    $('#c1_rsy').value = rsy1;
                    $('#c1_l2').value = l21;
                    $('#c1_r2').value = r21;

                    var ctlr2 = {
                        o: xouya.getO(2)
                      , u: xouya.getU(2)
                      , y: xouya.getY(2)
                      , a: xouya.getA(2)
                      , lsx: xouya.getLS_X(2)
                      , lsy: xouya.getLS_Y(2)
                      , rsx: xouya.getRS_X(2)
                      , rsy: xouya.getRS_Y(2)
                      , l2: xouya.getL2(2)
                      , r2: xouya.getR2(2)
                      , stickThreshold: 0.25
                      , triggerThreshold: 0.8
                    };

                    var lsx2 = (ctlr2.lsx > ctlr2.stickThreshold) ? parseFloat(ctlr2.lsx).toFixed(2) : 0
                      , lsy2 = (ctlr2.lsy > ctlr2.stickThreshold) ? parseFloat(ctlr2.lsy).toFixed(2) : 0
                      , rsx2 = (ctlr2.rsx > ctlr2.stickThreshold) ? parseFloat(ctlr2.rsx).toFixed(2) : 0
                      , rsy2 = (ctlr2.rsy > ctlr2.stickThreshold) ? parseFloat(ctlr2.rsy).toFixed(2) : 0
                      , l22 = (ctlr2.l2 > ctlr2.triggerThreshold) ? parseFloat(ctlr2.l2).toFixed(2) : 0
                      , r22 = (ctlr2.r2 > ctlr2.triggerThreshold) ? parseFloat(ctlr2.r2).toFixed(2) : 0
                    ;

                    lsx2 = (ctlr2.lsx < ctlr2.stickThreshold*-2) ? parseFloat(ctlr2.lsx).toFixed(2) : lsx2;
                    lsy2 = (ctlr2.lsy < ctlr2.stickThreshold*-2) ? parseFloat(ctlr2.lsy).toFixed(2) : lsy2;
                    rsx2 = (ctlr2.rsx < ctlr2.stickThreshold*-2) ? parseFloat(ctlr2.rsx).toFixed(2) : rsx2;
                    rsy2 = (ctlr2.rsy < ctlr2.stickThreshold*-2) ? parseFloat(ctlr2.rsy).toFixed(2) : rsy2;

                    $('#c2_o').value = ctlr2.o;
                    $('#c2_u').value = ctlr2.u;
                    $('#c2_y').value = ctlr2.y;
                    $('#c2_a').value = ctlr2.a;

                    $('#c2_lsx').value = lsx2;
                    $('#c2_lsy').value = lsy2;
                    $('#c2_rsx').value = rsx2;
                    $('#c2_rsy').value = rsy2;
                    $('#c2_l2').value = l22;
                    $('#c2_r2').value = r22;

                    if (parseInt(ctlr1.y) == 1) {
                        if (isPaused()) {
                            unpause();
                        } else {
                            pause();
                        }
                    }

                } catch(e) {
                    console.warn(e);
                }
            }
            _clock = window.requestAnimFrame(animLoop);
        }

      , getSprite = function() {
        }

      , getSprites = function() {
        }

      , setSprite = function() {
        }

      , start = function() {
            Game.log('game started');
            _clock = window.requestAnimFrame(animLoop);
        }

      , pause = function() {
            window.cancelAnimFrame(_clock);
            $('#pause-screen').style.display = 'block';
        }

      , unpause = function() {
            _clock = window.requestAnimFrame(animLoop);
            $('#pause-screen').style.display = 'none';
        }

      , setDebug = function(state) {
            _debug = state;
        }
    ;

    return {
        version: version
      , addSprite: addSprite
      , getSprite: getSprite
      , getSprites: getSprites
      , setSprite: setSprite
      , start: start
      , pause: pause
      , unpause: unpause
      , setDebug: setDebug
      , log: log
    }
})();
