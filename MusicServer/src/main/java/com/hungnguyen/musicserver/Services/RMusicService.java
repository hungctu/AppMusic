package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Entity.RecommendedMusic;
import com.hungnguyen.musicserver.Repository.RMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RMusicService {

    @Autowired
    private RMusicRepository repository;

    public String Capnhatluotnghe(int m_id,int u_id) {
        String success = "";
        if(u_id==0) {
            if(repository.baihatdanghe(m_id)!=null) {
                int cn = repository.capnhatluotnghe(m_id);
                System.out.println(cn+"u");
                if(cn==0) {
                    success="loi";
                }else {
                    success="success";
                }
            }else {
                int insert = repository.themluotnghe(m_id);
                System.out.println(insert+"i");
                if(insert==0) {
                    success="loi";
                }else {
                    success="success";
                }
            }
        }
        else {
            if(repository.baihatuserdanghe(u_id,m_id)!=null) {
                //Cap nhat luot nghe
                //System.out.println(rMapper.capnhatluotngheuser(rMusic));
                int cn = repository.capnhatluotngheuser(u_id,m_id);
                if(cn==0) {
                    success="loi";
                }else {
                    success="success";
                }
            }else {
                int insert = repository.themluotngheuser(u_id,m_id);
                //System.out.println(insert);
                if(insert==0) {
                    success="loi";
                }else {
                    success="success";
                }
            }
        }
        return "[{\"success\":\"" + success + "\"}]";
    }

    public String Capnhatcamxuc(int m_id,int u_id,int e_id) {
        String success = ""+u_id;

        if(u_id==0) {
            System.out.println("c");
            if(repository.baihatdanghecamxuc(m_id,e_id)!=null) {
                System.out.println("c1");
                if(repository.truluotnghe(m_id)==1) {
                    int cn = repository.capnhatcamxuc(m_id,e_id);
                    System.out.println(cn+"c");
                    if(cn==1) {
                        success="success";
                    }
                    else {
                        success="loi";
                    }
                }
            }else {
                System.out.println("c2");
                if(repository.truluotnghe(m_id)==1) {
                    int cn = repository.themcamxuc(m_id,e_id);
                    System.out.println(cn+"i");
                    if(cn==1) {
                        success="success";
                    }
                    else {
                        success="loi";
                    }
                }
            }
        }
        else {
            System.out.println("vao");
            if(repository.baihatuserdanghecamxuc(u_id,m_id,e_id)!=null) {
                System.out.println("vao1");
                if(repository.truluotngheuser(u_id,m_id)==1) {
                    System.out.println("vao3");
                    int cn = repository.capnhatcamxucuser(u_id,m_id,e_id);
                    System.out.println(cn+"c");
                    if(cn==1) {
                        success="success";
                    }
                    else {
                        success="loi";
                    }
                }
            }else {
                System.out.println("vao2");
                if(repository.truluotngheuser(u_id,m_id)==1) {
                    System.out.println("vao4");
                    int cn = repository.themcamxucuser(u_id,m_id,e_id);
                    System.out.println(cn+"i");
                    if(cn==1) {
                        success="success";
                    }
                    else {
                        success="loi";
                    }
                }
            }
        }
        return "[{\"success\":\"" + success + "\"}]";
    }

    public String Capnhatcamxuc2(int m_id,int u_id,int e_id) {
        String success = ""+u_id;
        if(u_id==0) {
            System.out.println("c");
            if(repository.baihatdanghecamxuc(m_id,e_id)!=null) {
                System.out.println("c1");
                int cn = repository.capnhatcamxuc(m_id,e_id);
                System.out.println(cn+"c");
                if(cn==1) {
                    success="success";
                }
                else {
                    success="loi";
                }
            }else {
                System.out.println("c2");
                int cn = repository.themcamxuc(m_id,e_id);
                System.out.println(cn+"i");
                if(cn==1) {
                    success="success";
                }
                else {
                    success="loi";
                }
            }
        }
        else {
            if(repository.baihatuserdanghecamxuc(u_id,m_id,e_id)!=null) {
                int cn = repository.capnhatcamxucuser(u_id,m_id,e_id);
                System.out.println(cn+"c");
                if(cn==1) {
                    success="success";
                }
                else {
                    success="loi";
                }

            }else {
                int cn = repository.themcamxucuser(u_id,m_id,e_id);
                System.out.println(cn+"i");
                if(cn==1) {
                    success="success";
                }
                else {
                    success="loi";
                }
            }
        }
        return "[{\"success\":\"" + success + "\"}]";
    }
}
