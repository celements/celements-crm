/**
 * Celements CRM Place Suggest
 * This is the Celements CRM auto complete for place input fields like zip, city.
 */
if(typeof CELEMENTS=="undefined"){var CELEMENTS={};};
if(typeof CELEMENTS.CRM=="undefined"){CELEMENTS.CRM={};};
if(typeof CELEMENTS.CRM.place=="undefined"){CELEMENTS.CRM.place={};};

(function(window, undefined) {

//////////////////////////////////////////////////////////////////////////////
// Celements CRM place suggest
//////////////////////////////////////////////////////////////////////////////
CELEMENTS.CRM.place.Suggest = function(cssSelector) {
  // constructor
  this._init(cssSelector);
};

(function() {
  CELEMENTS.CRM.place.Suggest.prototype = {
      _placeFields : undefined,
      _suggestPlaceRequest : null,

      _init : function(cssSelector) {
        var _me = this;
        _me._placeFields = $$(cssSelector);
      },

      getSuggestPlaces : function(callbackFunc) {
        var _me = this;
        var params = {
            'xpage' : 'celements_ajax',
            'ajax_mode' : 'SuggestPlace'
         };
        _me._placeFields.each(function(inputField) {
          params[inputField.name] = inputField.value;
        });
        if (_me._suggestPlaceRequest) {
          _me._suggestPlaceRequest.transport.abort();
          _me._suggestPlaceRequest = null;
        }
        _me._suggestPlaceRequest = new Ajax.Request(getCelHost(), {
          method: 'post',
          parameters: params,
          onSuccess: function(transport) {
            if (transport.responseText.isJSON()) {
              var responseObj = transport.responseText.evalJSON();
              _me._placeFields[0].up('form').fire('celcrm:PlaceSuggest', responseObj);
              if (callbackFunc) {
                callbackFunc(responseObj);
              }
            } else if ((typeof console != 'undefined')
                && (typeof console.warn != 'undefined')) {
              console.warn('getSuggestPlaces: noJSON!!! ', transport.responseText);
            }
          },
          onFailure : function(transport) {
            if ((typeof console != 'undefined')
                && (typeof console.warn != 'undefined')) {
              console.warn('getSuggestPlaces: failed to execute request for'
                + ' params [' + Object.toJSON(params) + ']: ', transport.status,
                transport.statusText);
            }
          },
          onComplete : function(transport) {
            _me._suggestPlaceRequest = null;
          }
        });
      }

  };
})();

})(window);
