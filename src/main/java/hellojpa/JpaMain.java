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

            Movie movie = new Movie();

            movie.setDirector("김감독");
            movie.setActor("홍길동");
            movie.setName("영화제목");
            movie.setPrice(10000);

            em.persist(movie);

            /*
            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            //team.getMembers().add(member);


            //em.flush();
            //em.clear();

            Team findTeam = em.find(Team.class, 1L);
            for (Member m : findTeam.getMembers()) {
            System.out.println("+++++++++++++++++");
                System.out.println(m.getId());
                System.out.println(m.getTeam().getName());
                System.out.println(m.getUsername());
            System.out.println("+++++++++++++++++");
            }
*/

/* 양방향 불러올때 개념

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
*/


            ts.commit();
        }catch (Exception e){
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
