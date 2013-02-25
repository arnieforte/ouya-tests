function Sprite(opts) {
    var opts = opts || {};
    var counter = 0;

    var src = opts.src || {
            x: 0
          , y: 0
          , w: 64
          , h: 64
          , url: ''
        }
      , x = opts. x || 0
      , y = opts. y || 0
      , xs = opts. xs || 0
      , ys = opts. ys || 0

      , uuid = ++counter
      , domNode = new Image()

      , placeAt = function(x, y) {
Game.log('Sprite.placeAt called: x:'+x + ', y:'+y);
            var el = ('domNode' in this) ? this.domNode : domNode;
            el.style.left = parseInt(x) + 'px';
            el.style.top = parseInt(y) + 'px';
        }

      , hide = function() {
            domNode.style.display = 'none';
        }

      , show = function() {
            domNode.style.display = 'block';
        }
    ;

    hide();
    domNode.style.position = 'absolute';
    domNode.style.width = src.w;
    domNode.style.height = src.h;
    domNode.src = src.url;
    document.body.appendChild(domNode);
    placeAt(x, y);
    show();

    return {
        uuid: uuid
      , domNode: domNode
      , hide: hide
      , show: show
      , placeAt: placeAt
    };
}
