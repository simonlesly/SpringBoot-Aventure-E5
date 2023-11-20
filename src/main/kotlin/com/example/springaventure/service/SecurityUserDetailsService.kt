package com.example.springaventure.service

import com.example.springaventure.model.dao.UtilisateurDao
import com.example.springaventure.model.entity.Utilisateur
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SecurityUserDetailsService constructor(
     val utilisateurDao: UtilisateurDao,
     val encoder: PasswordEncoder
) : UserDetailsService
{
    /***
     * Une méthode qui prend en param un nom d'utilisateur ou email qui provient du formulaire de login.
     * Et retourne un objet User (class provenant de Security)  avec un username,password (encoder), une liste de permissions
     * @param username peudo ou email du form de login
     * @return  un objet User (qui implement l'interface UserDetails)
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        val utilisateur: Utilisateur = utilisateurDao.findByEmail(username)
            ?: throw UsernameNotFoundException("Utilisateur non trouvé $username")

        val permissions: MutableSet<GrantedAuthority> = HashSet()
        for(unRole in utilisateur.roles){
            permissions.add(SimpleGrantedAuthority(unRole.nom))
        }

        return User(utilisateur.email, utilisateur.mdp, permissions)
    }
}