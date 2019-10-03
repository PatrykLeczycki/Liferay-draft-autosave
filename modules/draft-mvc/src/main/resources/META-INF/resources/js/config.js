
;(function() {

    var base = MODULE_PATH + '/js/';

    AUI().applyConfig(
            {
                groups: {
                    mymodulesoverride: { //mymodulesoverride
                        base: base,
                        combine: Liferay.AUI.getCombine(),
                        filter: Liferay.AUI.getFilterConfig(),
                        modules: {
                            'liferay-portlet-journal-ddm': { //my-module-override
                                path: 'ddm_form-override.js', //my-module.js
                                condition: {
                                    name: 'liferay-portlet-journal-ddm', //my-module-override
                                    trigger: 'liferay-ddm-form', //original module
                                    when: 'instead'
                                }
                            },
                        },
                        root: base
                    }
                }
            }
    );
})();
