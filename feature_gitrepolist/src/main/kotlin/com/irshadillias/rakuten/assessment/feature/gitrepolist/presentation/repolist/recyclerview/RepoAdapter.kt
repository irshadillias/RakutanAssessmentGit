package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.irshadillias.rakuten.assessment.feature.gitrepolist.R
import com.irshadillias.rakuten.assessment.feature.gitrepolist.databinding.FragmentAlbumListItemBinding
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryList
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.delegate.observer
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.extension.hide
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.extension.setOnDebouncedClickListener
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.extension.show

internal class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var repoList: List<RepositoryList> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((repo: RepositoryList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentAlbumListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    fun setOnDebouncedClickListener(listener: (repo: RepositoryList) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class ViewHolder(private val binding: FragmentAlbumListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var url by observer<String?>(null) {
            binding.coverErrorImageView.hide()

            if (it == null) {
                setDefaultImage()
            } else {
                binding.coverImageView.load(it) {
                    crossfade(true)
                    error(R.drawable.ic_image)
                    transformations(RoundedCornersTransformation(10F))
                }
            }
        }

        fun bind(repolist: RepositoryList) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(repolist) }
            url = repolist.imageUrl
            binding.username.text = repolist.name +" (type:${repolist.type})"
            binding.description.text= repolist.timestamp
        }

        private fun setDefaultImage() {
            binding.coverErrorImageView.show()
        }
    }
}
