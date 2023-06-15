package com.coderecipe.v1.admin.init.service;

import com.coderecipe.v1.admin.init.vo.InitAdminReq.InitRootUserSetupReq;

public interface InitAdminService {

    boolean setInitRootUser(InitRootUserSetupReq req);
}
