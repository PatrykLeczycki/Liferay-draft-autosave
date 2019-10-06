window.test = function() {

    console.log('test');

    var form = $('form#_com_liferay_journal_web_portlet_JournalPortlet_fm1');

    console.log('compare form = ', form);

    var url = form.attr('action');
    var p_auth = new URLSearchParams(url).get('p_auth');
    //var language = form.find('#_com_liferay_journal_web_portlet_JournalPortlet_languageId').attr('value');

    // var contentInput = form.find( "input[id^='_com_liferay_journal_web_portlet_JournalPortlet_content_INSTANCE_']" );
    // var shortContentInputId = contentInput.attr('id');
    //
    // var contentInputIdRandomPart = shortContentInputId.substr(shortContentInputId.length - 4, shortContentInputId.length);
    // var content = contentInput.attr('value');
    // var fixedContent = content.replace(/\r?\n|\r/g, '');    //removing unnecessary line breaks

    //var contentContainer = form.find('#dkdh__');

    var data = new FormData(form[0]);
    data.append('p_auth', p_auth);
    data.set('_com_liferay_journal_web_portlet_JournalPortlet_javax.portlet.action', 'updateArticle');
    //data.set('_com_liferay_journal_web_portlet_JournalPortlet_ddmFormValues', '{"availableLanguageIds":["' + language +'"],"defaultLanguageId":"' + language +'","fieldValues":[{"instanceId":"' + contentInputIdRandomPart + '","name":"content","value":{"' + language +'":"' + fixedContent + '"}}]}');

    //data.set('_com_liferay_journal_web_portlet_JournalPortlet_ddmFormValues', '{"availableLanguageIds":["en_US"],"defaultLanguageId":"en_US","fieldValues":[{"instanceId":"rxfe","name":"' + new Date() + '","value":{"en_US":"textbox___1"},"nestedFieldValues":[{"instanceId":"hxxd","name":"Booleange2a","value":{"en_US":"false"}}]},{"instanceId":"taim","name":"DocumentsAndMediaec4r","value":{"en_US":""}},{"instanceId":"zmwk","name":"Color8utg","value":{"en_US":"#FF0000"}}]}\n');
    //data.set('_com_liferay_journal_web_portlet_JournalPortlet_ddmFormValues', '    {"availableLanguageIds":["en_US"],"defaultLanguageId":"en_US","fieldValues":[{"instanceId":"rxfe","name":"TextBoxdb9s","value":{"en_US":"boolean = true"},"nestedFieldValues":[{"instanceId":"hxxd","name":"Booleange2a","value":{"en_US":"true"}}]},{"instanceId":"taim","name":"DocumentsAndMediaec4r","value":{"en_US":""}},{"instanceId":"zmwk","name":"Color8utg","value":{"en_US":"#FF0000"}}]}\n');

    url = url.replace('&p_auth='  + p_auth, '');

    var ddm = document.getElementById('_com_liferay_journal_web_portlet_JournalPortlet_ddmFormValues');

    //data.set('_com_liferay_journal_web_portlet_JournalPortlet_ddmFormValues', ddm);

    console.log('data = ', data);

    console.log('test ddm = ', ddm);

    $.ajax({
        url: url,
        data: data,
        cache: false,
        processData: false,
        contentType: false,
        method: 'POST',
        success: function(data, textStatus, jqXHR){
            form.find('#_com_liferay_journal_web_portlet_JournalPortlet_formDate').val(new Date().valueOf());
        }
    })
};

function toJSON(){

    var form = $('form#_com_liferay_journal_web_portlet_JournalPortlet_fm1');

    //data.set('_com_liferay_journal_web_portlet_JournalPortlet_ddmFormValues', '{"availableLanguageIds":["' + language +'"],"defaultLanguageId":"' + language +'","fieldValues":[{"instanceId":"' + contentInputIdRandomPart + '","name":"content","value":{"' + language +'":"' + fixedContent + '"}}]}');
    var ddm = '{"availableLanguageIds":"' + getAvailableLanguagesIds(form) +',"defaultLanguageId":"' + getDefaultLanguageId(form) + ',"fieldValues":}';

    return ddm;
}

function getFieldValues(){

}

function getInstanceId(elem){
    return elem.id.substr(elem.id.length - 4, elem.id.length);
}

function getName(elem){
    return elem.dataset.fieldname;
}

function getAvailableLanugagesIdsArray(form){
    var titleMap = form.find("input[id^='_com_liferay_journal_web_portlet_JournalPortlet_titleMapAsXML_']");

    var titleMapToArray = [];

    Array.prototype.forEach.call(titleMap, entry => {
        titleMapToArray.push(entry.id.slice(-5));
    });

    return titleMapToArray;
}

function getAvailableLanguagesIds(form){

    var availableLanguagesIdsArray = getAvailableLanugagesIdsArray(form);

    var titleMapToString = '[';

    Array.prototype.forEach.call(availableLanguagesIdsArray, elem => {

        titleMapToString += '"' + elem + '",';
    });
    titleMapToString = titleMapToString.slice(0, -1);
    titleMapToString += ']';

    return titleMapToString;
}

function getDefaultLanguageId(form){
    return form.find('#_com_liferay_journal_web_portlet_JournalPortlet_languageId').attr('value');
}