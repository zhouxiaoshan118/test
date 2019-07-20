<!DOCTYPE html>
<html>
  <head>
  <meta charset="utf-8">
  <title>个人详情</title>
  <meta name="keywords" content="个人信息" />
  <meta name="description" content="个人信息" />
  <link rel="stylesheet" type="text/css" href="${basePath}/plugins/officer/css/style.css" />
  <link rel="stylesheet" type="text/css" href="${basePath}/plugins/officer/css/new.css" media="all"/>
  <link rel="stylesheet" href="${basePath}/plugins/officer/css/bootstrap.min.css?v=2159" />
  <link rel="stylesheet" href="${basePath}/plugins/officer/css/zxbj_base.css?v=2818" />
  <link rel="stylesheet" href="${basePath}/plugins/officer/css/jm0203.css" />
  <style>
    .m-nav-ul .nav_li:nth-child(3) a.erji_a {
      color: #F66000;
    }
    .m-nav-ul .nav_li:nth-child(3) a.triangle {
      border-color: #F66000;
    }
    .m-top_user li a {
      box-sizing: content-box;
      -moz-box-sizing: content-box;
      -webkit-box-sizing: content-box;
    }
    .m-erjinav-ul {
      margin-top: 25px;
    }
</style>
</head>
<body style="height:auto;background:url(${basePath}/plugins/officer/img/2018-1-3.jpg) center top no-repeat;background-size: 100%;">
<div class="n_dispage_jm" style="margin:0 auto">
    <div class="n_dispage_jm_in resetEditStyle">

    <!--编辑区域-->

    <div class="baseStyle clearfix jm0203_j1" id="resume_body">
      <div class="divLeft clearfix" id="bar">

        <!--头像-->
		
        <div data-toggle="modal" data-target="#myModalHead" class="headDiv" id="resume_head"> <span class="headNotice">点击替换头像</span> <img class="resume_head" src="${officer.image}" height="200" width="150"/> </div>
        <div id="bar_sort"></div>

        <!--基本信息-->

        <div class="msgDiv positonDiv resume_add_area resume_sort" id="resume_msg">
                  <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus resume_add" for-key="resume_msg" data-placement="bottom" title="添加一项"></li>
          </ul>
            <div class="baseAge baseMsg baseDel" id="resume_age">
            <div class="delete resume_hidden" for-id="resume_age"></div>
            <a class="resume_icon_diy icon wbdfont" for-id="sex" style="font-size:none"></a>
            <input class="baseBorder resume_msg resume_lang_age" for-key="age" placeholder="性别" value="${(officer.sex =='1')?string('男','女')?html}" />
          </div>
                  <div class="baseAddress baseMsg baseDel" id="resume_address">
            <div class="delete resume_hidden" for-id="resume_address"></div>
            <a class="resume_icon_diy icon wbdfont" for-id="address" style="font-size:none"></a>
            <input class="resume_msg baseBorder resume_lang_address" for-key="address" placeholder="联系地址" value="${(officer.address?exists) ?string('officer.address', '未填写')}" />
          </div>
                  <div class="baseMobile baseMsg baseDel" id="resume_mobile">
            <div class="delete resume_hidden" for-id="resume_mobile"></div>
            <a class="resume_icon_diy icon wbdfont" for-id="mobile" style="font-size:none"></a>
            <input class="resume_msg baseBorder resume_lang_mobile" for-key="mobile" placeholder="联系电话" value="${officer.telephone?exists ?string(officer.telephone, '未填写')}" />
          </div>
                  <div class="baseEmail baseMsg baseDel" id="resume_email">
            <div class="delete resume_hidden" for-id="resume_email"></div>
            <a class="resume_icon_diy icon wbdfont" for-id="email" style="font-size:none"></a>
            <input class="resume_msg baseBorder resume_lang_email" for-key="email" placeholder="电子邮箱" value="${officer.email?exists ?string(officer.email, '未填写')}" />
          </div>
      </div>

        <!--个人技能-->

        <div class="skillDiv positonDiv resume_add_area resume_graph resume_sort" id="resume_graph">
          <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus resume_add" for-key="resume_graph" data-placement="bottom" title="添加一项"></li>
            <li class="btnDel resume_hidden" for-id="resume_graph" data-placement="bottom" title="隐藏该模块"></li>
          </ul>
          <ul class="skillUl resume_append_area">
          	
          	<#if officer.skill!="">
          		<#list officer.skill?split(",") as smallSkill>
          			<#if smallSkill_index<8>
	          			<li class="baseDel resume_delete_area resume_graph_item">
			              <div class="skillTitle">
			                <input class="resume_value baseBorder" for-key="resume_skill_graph" placeholder="填写你的技能名称" value="${smallSkill}" />
			              </div>
			              <span><i class="resume_line" for-id="resume_skill_graph" style="width:128px;border-top-style:solid;border-top-width:8px;"></i></span>
			              <div class="delete resume_delete"></div>
	            		</li>
            		</#if>
          		</#list>
          	</#if>
          
            

        <!--兴趣爱好-->
		
        <div class="hobbyDiv positonDiv resume_add_area resume_icon resume_sort" id="resume_icon">
                  <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus resume_add" for-key="resume_icon" data-placement="bottom" title="添加一项"></li>
            <li class="btnDel resume_hidden" for-id="resume_icon" data-placement="bottom" title="隐藏该模块"></li>
          </ul>
                  <dl class="clearfix">
            <dt><span>
            <#if officer.hobby!="">
              <div class="resume_lang_hobby">兴趣爱好</div>
              </span></dt>
            <dd class="resume_append_area">
            	
            	<#list officer.hobby?split(",") as smallhobby>
            		<#if smallhobby_index<2>
		                 <div class="hobbyicon baseDel resume_delete_area resume_icon_item"> <a class="resume_icon_diy icon wbdfont" for-id="resume_hobby_icon" style="font-size:none"></a> <span class="hobbyTitle">
		                        <input class="resume_value baseBorder" for-key="resume_hobby_icon" placeholder="兴趣爱好" value="${smallhobby}" />
		                        </span>
		                		<div class="delete resume_delete"></div>
		                </div>
	                </#if>		
                </#list>
              	</#if>  
              </div>
            
                    </dd>
          </dl>
                </div>
      </div>

              <div class="divRight clearfix resume_main" id="foo">
	     
        <!--姓名、求职意向-->

        <div class="nameDiv positonDiv" id="resume_name">
                  <h1>
            <div class="resume_msg baseBorder resume_notice resume_lang_name" notice-key="msg" for-key="name" for-value="html" contenteditable="true">${officer.name}</div>
          </h1>
                  <h2>
            <div class="resume_msg baseBorder resume_notice resume_lang_job" notice-key="job" for-key="job" for-value="html" contenteditable="true">现任职位：${officer.job}</div>
          </h2>
                </div>
        <div id="foo_sort"></div>
	
        <!--教育背景-->
		<#if officer.educational!="">
        <div class="eduDiv baseItem positonDiv resume_item resume_add_area resume_sort resume_notice" notice-key="edu" id="resume_edu" for-key="edu">
                  <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus resume_add" for-key="resume_item" data-placement="bottom" title="添加一项"></li>
            <li class="btnDel resume_hidden" for-id="resume_edu" data-placement="bottom" title="隐藏该模块"></li>
          </ul>
             <dl>
            <dt> <a class="resume_icon_diy icon wbdfont" for-id="edu" style="font-size:none"></a> <span>
              <div class="resume_lang_edu">教育背景</div>
              <div class="Border resume_line" for-id="edu" style="width:668px;border-top-style:solid;border-top-width:2px;"></div>
              </span> </dt>
            <dd class="resume_append_area">
                      <div class="baseContent baseDel resume_item_items resume_delete_area">
                <div class="delete resume_delete"></div>
                <div class="conTitle"> <span class="conTitle_time">
                  
                  </span> </div>
                <div class="baseDel_ resume_delete_area_">
                          <div class="delete_ resume_delete_"></div>
                          <div class="conDisc baseBorder baseDel resume_value" contenteditable="true" data-toggle="tooltip" data-placement="top" title="复制粘贴文字请右键-粘贴为纯文本">
                    		${officer.educational}
                 </div>
                       </div>
              				</div>
                    </dd>
          </dl>
               
             
             
           
         </div>     
		</#if>
        <!--工作经历-->
		<#if officer.educational!="">
        <div class="workDiv baseItem positonDiv resume_item resume_add_area resume_sort resume_notice" notice-key="work" id="resume_work" for-key="work">
                  <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus resume_add" for-key="resume_item" data-placement="bottom" title="添加一项"></li>
            <li class="btnDel resume_hidden" for-id="resume_work" data-placement="bottom" title="隐藏该模块"></li>
          </ul>
                  <dl>
            <dt> <a class="resume_icon_diy icon wbdfont" for-id="work" style="font-size:none"></a> <span>
              <div class="resume_lang_work">工作经历</div>
              <div class="Border resume_line" for-id="work" style="width:668px;border-top-style:solid;border-top-width:2px;"></div>
              </span> </dt>
            <dd class="resume_append_area">
                <div class="baseContent baseDel resume_item_items resume_delete_area">
               	${officer.work}
              </div>
                    </dd>
          </dl>
                </div>
		</#if>
        <!--项目经验-->
		<#if officer.experience!="">
        <div class="outexperDiv baseItem positonDiv resume_item resume_add_area resume_sort resume_notice" notice-key="project" id="resume_project" for-key="project">
                  <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus resume_add" for-key="resume_item" data-placement="bottom" title="添加一项"></li>
            <li class="btnDel resume_hidden" for-id="resume_project" data-placement="bottom" title="隐藏该模块"></li>
          </ul>
                  <dl>
            <dt> <a class="resume_icon_diy icon wbdfont" for-id="project" style="font-size:none"></a> <span>
              <div class="resume_lang_project">项目经验</div>
              <div class="Border resume_line" for-id="project" style="width:668px;border-top-style:solid;border-top-width:2px;"></div>
              </span> </dt>
            <dd class="resume_append_area">
               <div class="baseContent baseDel resume_item_items resume_delete_area">
                <div class="delete resume_delete"></div>
                <div class="baseDel_ resume_delete_area_">
                          <div class="delete_ resume_delete_"></div>
                          <div class="conDisc baseBorder resume_value" contenteditable="true" data-toggle="tooltip" data-placement="top" title="复制粘贴文字请右键-粘贴为纯文本">
                    	${officer.experience}
                  		</div>
                 </div>
              </div>
                    </dd>
          </dl>
                </div>
		</#if>	
        <!--自我评价-->
		<#if officer.evaluate!="">
        <div class="selfDiv baseItem positonDiv resume_item resume_sort resume_notice" notice-key="self" id="resume_self" for-key="self">
          <ul class="editBtn">
            <li class="btnMove" style="cursor:move;"data-placement="top" title="拖动调整上下位置"></li>
            <li class="btnPlus" data-placement="bottom" title="添加一项" style="display:none;"></li>
            <li class="btnDel resume_hidden" for-id="resume_self" data-placement="bottom" title="隐藏该模块"></li>
          </ul>
                  <dl>
            <dt> <a class="resume_icon_diy icon wbdfont" for-id="self" style="font-size:none"></a> <span>
              <div class="resume_lang_self">自我评价</div>
              <div class="Border resume_line" for-id="self" style="width:668px;border-top-style:solid;border-top-width:2px;"></div>
              </span> </dt>
            <dd>
                      <div class="baseContent">
                <div class="conDisc baseBorder resume_lang_self_value resume_value" contenteditable="true" data-toggle="tooltip" data-placement="top" title="复制粘贴文字请右键-粘贴为纯文本">
                          ${officer.evaluate}
                        </div>
              </div>
                    </dd>
          </dl>
                </div>
           </#if>     
      </div>
		
    </div>
  </div>
</div>
</body>
</html>
