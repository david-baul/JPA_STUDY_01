package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, 2L);
            System.out.println("@@@@@@@@@@@@@@@@@@"+findMember.getId());
            System.out.println("@@@@@@@@@@@@@@@@@@"+findMember.getUsername());
            System.out.println("@@@@@@@@@@@@@@@@@@"+findMember.getTeam().getId());
            System.out.println("@@@@@@@@@@@@@@@@@@"+findMember.getTeam().getName());



            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("####" + m.getId());
                System.out.println("####" + m.getTeam().getName());
                System.out.println("####" + m.getUsername());
            }

            System.out.println("@@@@@@@@@@@@@@@@@@"+findMember.getTeam().getMembers());

//            Member findMember = em.find(Member.class, member.getId());
//
//            Team findTeam = findMember.getTeam();
//            System.out.println("*********************findTeam = " + findTeam.getName());

//            Member findMember = em.find(Member.class, member.getTeamId());
//            Team findTeam = em.find(Team.class, findMember.getTeamId());

            ts.commit();
        }catch (Exception e){
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
