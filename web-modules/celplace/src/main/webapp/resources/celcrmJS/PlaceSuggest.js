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

      _init : function(cssSelector) {
        var _me = this;
        _me._placeFields = $$(cssSelector);
      },

      getSuggestPlaces : function() {
        var _me = this;
        var params = {
            'xpage' : 'celements_ajax',
            'ajax_mode' : 'SuggestPlace'
         };
        _me._placeFields.each(function(inputField) {
          params[inputField.name] = inputField.value;
        });
        new Ajax.Request(getCelHost(), {
          method: 'post',
          parameters: params,
          onSuccess: function(transport) {
            if (transport.responseText.isJSON()) {
              //TODO
              console.log('getSuggestPlaces: success ', transport.responseText);
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
          }
        });
      }

  };
})();

})(window);
