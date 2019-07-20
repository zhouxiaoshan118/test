'use strict';
/**
 * jquery.gDialog.js
 * @version: v0.1.0
 * @author: ogilvieira
 *
 * The MIT License (http://www.opensource.org/licenses/mit-license.php)
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

(function (factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        module.exports = factory(require('jquery'));
    } else {
        factory(jQuery || Zepto);
    }	
}(function($){
    var m = {};
 	var g = {};
    m.OPENING = false;
    m._OPTIONS = {
        title: false,
        animateIn : false,
        animateOut : false,
        onSubmit : false,
        onCancel : false,
        required: false, 
        okbtn: 'OK',
        cencelbtn: 'Cancel',
        isColse:true
    };

    m.tplBase = "<div class=\"gdialog-wrap\">";
    m.tplBase += "<div class=\"gdialog-container\">";
    m.tplBase += "{{HEADER}}";
    m.tplBase += "<div class=\"gdialog-content\">{{message}}{{INPUT}}</div>";
    m.tplBase += "<div class=\"gdialog-button-group\">{{BUTTON_CANCEL}} {{BUTTON_OK}}</div>";
    m.tplBase += "</div>";
    m.tplBase += "</div>";

    m.tplHeader = "<div class=\"gdialog-header\">{{title}}</div>";
    m.tplInput = "<div class=\"gdialog-field\"><input type=\"text\"></div>";
    m.tplOkbtn = "<button class=\"button button-ok\">{{Ok}}</button></div>";
    m.tplCencelbtn = "<button class=\"button button-cancel\">{{Cancel}}</button>";

    m.getTeplate = function(type, message, options){
        var t = m.tplBase;
        if( type !== 'alert' ){ 
            t = t.replace("{{BUTTON_CANCEL}}", m.tplCencelbtn.replace("{{Cancel}}", options.cencelbtn) );
            t = t.replace("{{BUTTON_OK}}", m.tplOkbtn.replace("{{Ok}}", options.okbtn) ); 
        } else {
            t = t.replace("{{BUTTON_CANCEL}}", ""); 
            t = t.replace("{{BUTTON_OK}}", m.tplOkbtn.replace("{{Ok}}", options.okbtn) ); 
        }
        if( type == 'prompt' ){ 
            t = t.replace("{{INPUT}}", m.tplInput); 
        } else {
            t = t.replace("{{INPUT}}", ""); 
        }        
        if( options.title ){ 
            t = t.replace("{{HEADER}}", m.tplHeader.replace("{{title}}", options.title) ); 
        } else {
            t = t.replace("{{HEADER}}", ""); 
        }
        t = t.replace("{{message}}", message ); 
        return t;
    };

    m.clear = function(){
        $('.gdialog-shadow').length ? $('.gdialog-shadow').remove() : '';
        $('.gdialog-wrap').length ? $('.gdialog-wrap').remove() : '';
    };

    m.Dialog = function(){
        var that = this;

        that.close = function(){
            $('.gdialog-shadow').addClass("animated fadeOut");
            if( that.options.animateOut ){
                if( that.options.animateIn ){ that.container.find('.gdialog-container').removeClass(that.options.animateIn) }
                that.container.find('.gdialog-container').addClass('animated '+that.options.animateOut);
                setTimeout(function(){
                    that.container.removeClass('is-active');
                    that.container.remove();
                    m.OPENING = false;
                    $('.gdialog-shadow').remove();
                }, 800);
            }else {
                that.container.remove();
                m.OPENING = false;
                $('.gdialog-shadow').remove();
            }
        };

        that.addEvents = function(){
            that.btnOk.on("click", function(e){
            		e.preventDefault();
            		if(that.options.required){
            			
            			that.close()
            			
            		}else{
            			var title = $("#articleTitle").val();
        		    	var description = $("#description").val();
        		    	var content = editor.txt.text();
        		    	
        		    	if($.trim(title)==""||$.trim(description)==""||$.trim(content)==""){
        		    		$(".info").text("请完善日报信息");
        		    		return;
        		    	}
        		    	
        		    	$.ajax({
        		    		type:"post",
        		    		url:"/winui/dailyarticle/publicArticle",
        		    		data:{
        		    			title:title,
        		    			description:description,
        		    			content:content,
        		    			articleRoot:0
        		    		},
        		    		success:function(data){
        		    			var html="";
        		    			html += "<li class='ul-list' style='position:relative'>";
        						html += "<div class='imgbox' style='border:1px solid #ccc;border-radius: 5px;'>";
        						html += "<img src=/plugins/dailyArticle/img/xq"+data.week+".png alt='' width='250px' height='150px'>";
        						html += "</div>";
        						html += "<div class='font-box'>";
        						html += "<div class='font-title'>"+data.title+"</div>";
        						html += "<div class='font-info'>";
        						html += handleDis(data.description);
        						html += "<a href='javascript:;' class='demo-1' value='"+content+"'>[阅读全文]</a>";
        						html += "</div>";
        						html += "<div class='font-time'>";
        						html += "<div class='fb-user'><img src='"+data.officer.image+"' width='30px' height='30px' /><span><span class='lightfont'>"+data.officer.name+"</span>发布于:</span><span>"+data.stringPushDate+"</span></div>";
        						html += "</div>";
        						html += "</div>";
        						html += "</li>";
        						
        						$("#articleContent").prepend(html);
        						that.close();
        		    		}
        		    	})
            		}
            
            		
            	
            	
            	
                //that.close();
            });
            that.btnCancel.on("click", function(e){
                e.preventDefault();
                var res = false;
                if( that.field.length && that.field.val().length !== 0 ){ 
                    res = that.field.val(); 
                }
                if( typeof that.options.onCancel == 'function' ){
                    that.options.onCancel(res);
                }
                that.close();
            });
        }

        this.init = function(type, message, options, defaultValue){
            if( m.OPENING ){ $('.gdialog-shadow, .gdialog-wrap').remove(); }
            m.clear();

            that.options = m.getOptions(options);

            $('body').append("<div class=\"gdialog-shadow\"></div> "+m.getTeplate(type, message, that.options) );
            that.container = $('body').find('.gdialog-wrap');
            that.btnOk = that.container.find('.button-ok');
            that.btnCancel = that.container.find('.button-cancel');
            that.field = that.container.find('input');
            if( defaultValue && that.field.length ){
                that.field.val(defaultValue);
            };

            that.container.addClass('is-active').css({'top': $(window).scrollTop()+50});
            if( that.options.animateIn ){
                that.container.find('.gdialog-container').addClass('animated '+that.options.animateIn);
            }
            m.OPENING = true;

            that.addEvents();
        };
    };

    m.getOptions = function(options){
        var o = $.extend({}, m._OPTIONS);

        if( typeof options == 'object' ){
          $.each(options, function(key, val){
            o[key] !== undefined ? o[key] = val : console.error("The option \""+key+"\" not exist.");
          });
        }

        return o;
    };

    //global functions
    g.alert = function(message, userOptions){
        var message = message || "";
        var userOptions = userOptions || {};
        var dialog = new m.Dialog;
        dialog.init('alert', message, userOptions);
    };

    g.confirm = function(message, userOptions){
        var message = message || "";
        var userOptions = userOptions || {};
        var dialog = new m.Dialog;
        dialog.init('confirm', message, userOptions);
    };

    g.prompt = function(message, defaultValue, userOptions){
        var message = message || "";
        var userOptions = userOptions || {};
        var dialog = new m.Dialog;
        dialog.init('prompt', message, userOptions, defaultValue);
    };

    g.config = function(options){
        if( typeof options !== 'object' ){ return false; }
        $.each(options, function(key, val){
            m._OPTIONS[key] !== undefined ? m._OPTIONS[key] = val : console.error("The option \""+key+"\" not exist.");
        });
    };

    $.gDialog = $.gDialog || g;
}));
//www.sucaijiayuan.com