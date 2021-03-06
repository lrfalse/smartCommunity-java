package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.commons.dto.anDto.LoginDTO;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.FeedBackReDto;
import com.commons.entity.FeedBackEntity;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.RedisService;
import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
import com.commons.utils.DateUtils;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.dubbo.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


/**
 * @Description(功能描述): 用户
 * @author(作者): lrfalse<wangliyou>
 * @date (开发日期): 2018/4/19 10:59
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    /**
     * @Description(功能描述): 新增用户
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/20 16:49
     **/
    public int addUser(UserEntity user) {
        return userMapper.insert(user);
    }

    /**
     * @Description(功能描述): 用户用户对象查询用户信息
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/24 10:35
     **/
    public UserEntity getUser(UserEntity user) {
        return userMapper.selectOne(user);
    }

    /**
     * @Description(功能描述): 保存用户信息
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/25 9:41
     **/
    public UserEntity saveUser(UserEntity user) {
        ParamDto dto = new ParamDto();
        dto.put("mobPhone_where", user.getMobPhone());
        UserEntity isExtis = userMapper.selectUser(dto);                        //判断用户手机号码是否存在
        if (CommonUtils.isEmpty(isExtis)) {
            if (userMapper.adduser(user) < 0) {
                throw new ScException(AppServiceEnums.SYS_EXCEPTION);        //系统异常
            }
        } else {
            throw new ScException(AppServiceEnums.USER_EXIST);
        }
        ParamDto paramDto= new ParamDto();
        paramDto.put("userId", user.getId());
        paramDto.put("type", "1");
        int im = userMapper.addUserImgUrl(paramDto);
        if (im<0){
            throw new ScException(AppServiceEnums.SYS_EXCEPTION);
        }
        return user;
    }

    /**
     * @Description(功能描述): 分页查询 test用
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/26 20:17
     **/
    public Page<UserEntity> getPageUser(UserEntity user) {
        ParamDto dto = new ParamDto();
        Page<UserEntity> page = PageHelper.startPage(dto.getPage(), dto.getRows());
        dto.clear();
        dto.put("mobPhone_where", user.getMobPhone());
        userMapper.selectListUser(dto);
        return page;
    }

    /**
     * @Description(功能描述): 意见反馈
     * @author(作者): feihong
     * @date (开发日期):2018/5/10 17:20
     **/
    @Override
    public int addFeedBackxx(FeedBackReDto feedBackReDto) {
        FeedBackEntity feedBackEntity = bulidFeedBack(feedBackReDto);
        int i = userMapper.addFeedBack(feedBackEntity);
        if (i>0) {
            List<String> list = feedBackReDto.getList();
            for (String str : list) {
                ParamDto paramDtoUrl = new ParamDto();
                paramDtoUrl.put("id",feedBackEntity.getId());
                paramDtoUrl.put("imgUrl",str);
                int i1 = userMapper.addFeedBackImg(paramDtoUrl);
            }
            return 1;
        }
        return 0;
    }


    /**
     * @Description(功能描述): 找回密码
     * @author(作者): feihong
     * @date (开发日期):2018-4-24 20:02:21
     **/
    public int updateUserByKey(UserEntity userEntity) {
        UserEntity entity = userMapper.selectPhone(userEntity.getMobPhone());
        if (CommonUtils.isEmpty(entity)) {
            throw new ScException(AppServiceEnums.PHONE_NOT_EXIST);
        }
        userEntity.setPwd(MD5Utils.md5(userEntity.getPwd()));
        return userMapper.updatePwd(userEntity.getMobPhone(), userEntity.getPwd());
    }

    /**
     * @Description(功能描述): 绑定手机号码
     * @author(作者): feihong
     * @date (开发日期):2018-4-24 14:47:43
     **/
    public LoginDTO bindPhone(UserEntity entity) {
        UserEntity user = userMapper.selectPhone(entity.getMobPhone());
        //注册过
        if (CommonUtils.isNotEmpty(user)) {
            ParamDto paramDto = new ParamDto();
            paramDto.put("userId", user.getId());
            paramDto.put("imgUrl", entity.getImgUrl());
            paramDto.put("type", "1");
            if (entity.getToken().equals("Q") || entity.getToken().equals("W")) {
                int qi = userMapper.updateUser(bulidParamDto(entity));
                if (qi < 0) {
                    throw new ScException(AppServiceEnums.SYS_EXCEPTION);
                }
                ParamDto paramImgDto = new ParamDto();
                paramImgDto.put("id", user.getId());
                String s = userMapper.queryUserImgId(paramImgDto);
                if (s == null) {
                    int qimg = userMapper.addUserImgUrl(paramDto);
                    if (qimg < 0) {
                        throw new ScException(AppServiceEnums.SYS_EXCEPTION);
                    }
                    return bulidLoginDtoPone(user);
                }
            }
            //没有注册过
        } else {
            if (entity.getSex().equals("男")) {
                entity.setSex("0");
            } else {
                entity.setSex("1");
            }
            int insert = userMapper.adduser(entity);
            if (insert > 0) {
                ParamDto param = new ParamDto();
                param.put("userId", entity.getId());
                param.put("imgUrl", entity.getImgUrl());
                param.put("type", "1");
                ParamDto paramImgDto = new ParamDto();
                paramImgDto.put("id", entity.getId());
                String s = userMapper.queryUserImgId(paramImgDto);
                if (s == null) {
                    int imgUrl = userMapper.addUserImgUrl(param);
                    if (imgUrl > 0) {
                        return bulidLoginDtoPone(entity);
                    }
                }
            }
            throw new ScException(AppServiceEnums.SYS_EXCEPTION);
        }
        throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
    }

    /**
     * @Description(功能描述): 登录
     * @author(作者): feihong
     * @date (开发日期):2018-4-24 10:35:44
     **/
    public LoginDTO logIn(UserEntity entity) throws ScException {
        if (entity.getTag() == null) {
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        } else {
            //QQ登录
            if (entity.getTag().equals("Q")) {
                if (CommonUtils.isNotEmpty(entity.getQopenId())) {
                    ParamDto dto = new ParamDto();
                    dto.put("qopenId", entity.getQopenId());
                    UserEntity user = userMapper.queryUser(dto);
                    if (CommonUtils.isEmpty(user)) {
                        return bulidLoginDto(entity);
                    }
                    LoginDTO loginDTO = bulidLoginDto(user);
                    return loginDTO;
                }
                throw new ScException(AppServiceEnums.ERROR);
                //微信登录
            } else if (entity.getTag().equals("W")) {
                if (CommonUtils.isNotEmpty(entity.getWopenId())) {
                    ParamDto dto = new ParamDto();
                    dto.put("wopenId", entity.getWopenId());
                    UserEntity user = userMapper.queryUser(dto);
                    if (CommonUtils.isEmpty(user)) {
                        return bulidLoginDto(entity);
                    }
                    return bulidLoginDto(user);
                }
                throw new ScException(AppServiceEnums.ERROR);
                //手机登录
            } else if (entity.getTag().equals("P")) {
                if (CommonUtils.isNotEmpty(entity.getMobPhone()) && CommonUtils.isNotEmpty(entity.getPwd())) {
                    ParamDto dto = new ParamDto();
                    dto.put("pwd", MD5Utils.md5(entity.getPwd()));
                    dto.put("mobPhone", (entity.getMobPhone()));
                    UserEntity userEntity = userMapper.queryUser(dto);
                    if (CommonUtils.isEmpty(userEntity)) {
                        throw new ScException(AppServiceEnums.ERROR);
                    } else {
                        return bulidLoginDto(userEntity);
                    }
                }
                throw new ScException(AppServiceEnums.ERROR);
            }
        }
        return bulidLoginError();
    }




    /**
     * @Description(功能描述): 绑定手机对象构建
     * @author(作者): feihong
     * @date (开发日期):2018/5/4 18:56
     **/
    public ParamDto bulidParamDto(UserEntity entity) {
        ParamDto paramDto = new ParamDto();
        paramDto.put("name", entity.getName());
        paramDto.put("sex", entity.getSex());
        if (entity.getToken().equals("Q")) {
            paramDto.put("qopenId", entity.getQopenId());
        } else {
            paramDto.put("wopenId", entity.getWopenId());
        }

        paramDto.put("mobPhone_where", entity.getMobPhone());
        return paramDto;
    }

    /**
     * @Description(功能描述): 对象构建
     * @author(作者): feihong
     * @date (开发日期):2018-4-19 9:48:32
     **/
    public LoginDTO bulidLoginDto(UserEntity user) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setImg_url(user.getImgUrl());
        loginDTO.setName(user.getName());
        loginDTO.setSex(user.getSex());
        loginDTO.setCommuntiyId(user.getCommunityId());
        loginDTO.setMobphone(user.getMobPhone());
        loginDTO.setUserId(user.getId());
        if (CommonUtils.isEmpty(user.getMobPhone())){
            loginDTO.setToken(null);
        }else {
            loginDTO.setToken(MD5Utils.md5(user.getMobPhone()));
            try {
                saveUserForRedis(loginDTO);	//用户信息存入缓存
            } catch (Exception e) {
                e.printStackTrace();
            }
            // loginDTO.setUserId(null);
        }
        return loginDTO;
    }
    /**
     * @Description(功能描述): 缓存用户信息
     * @author(作者): lrfalse<wangliyou>
     * @date(开发日期): 2018/5/9 10:38
     **/
    public void saveUserForRedis(LoginDTO loginDTO)  {
        String phone=loginDTO.getMobphone();
        String token=MD5Utils.md5(phone);
        try {
            redisService.setNoTime(token, JsonUtils.toJson(loginDTO));
        } catch (Exception e) {
            throw new ScException(AppServiceEnums.SYS_EXCEPTION);
        }
    }
    /**
     * @Description(功能描述): 删除用户缓存信息
     * @author(作者): feihong
     * @date (开发日期):2018/5/9 11:26
     **/
    public void removeUserForRedis(String token) {
        redisService.delete(token);
    }
    /**
     * @Description(功能描述): 根据token获取缓存数据
     * @author(作者): lrfalse<wangliyou>
     * @date(开发日期): 2018/5/9 10:31
     **/
    public LoginDTO getRedisUser(String token)   {
        if(CommonUtils.isNotEmpty(redisService.get(token))){
            String userStr=redisService.get(token);
            String userDto= (String) JSON.parse(userStr);
            return JSON.parseObject(userDto,LoginDTO.class);
        }
        throw new ScException(AppServiceEnums.NULL_USER_DATA);
    }


    /**
     * @Description(功能描述): 手机登陆返回对象
     * @author(作者): feihong
     * @date (开发日期):2018-4-21 9:48:32
     **/
    public LoginDTO bulidLoginDtoPone(UserEntity entity) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMobphone(entity.getMobPhone());
        loginDTO.setCommuntiyId(entity.getCommunityId());
        loginDTO.setImg_url(entity.getImgUrl());
        loginDTO.setName(entity.getName());
        if (entity.getSex().equals("0") | entity.getSex().equals("男")) {
            loginDTO.setSex("男");
        } else {
            loginDTO.setSex("女");
        }

        return loginDTO;
    }

    /**
     * @Description(功能描述): 登录错误
     * @author(作者): feihong
     * @date (开发日期):2018-4-24 11:58:32
     **/
    public LoginDTO bulidLoginError() {
        LoginDTO loginDTO = new LoginDTO();
        return loginDTO;
    }

    /**
     * @Description(功能描述): 意见反馈
     * @author(作者): feihong
     * @date (开发日期):2018/5/10 17:22
     **/
    public FeedBackEntity bulidFeedBack(FeedBackReDto feedBackReDto){
        FeedBackEntity feedBackEntity = new FeedBackEntity();
        feedBackEntity.setStatus("0");
        feedBackEntity.setContext(feedBackReDto.getContext());
        feedBackEntity.setFeedbackTime(DateUtils.getDateStringForYMDHMS());
        feedBackEntity.setUserId(feedBackReDto.getUserId());
        return feedBackEntity;
    }
}
