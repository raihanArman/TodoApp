package id.co.todoapp.databinding;
import id.co.todoapp.R;
import id.co.todoapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentListBindingImpl extends FragmentListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recyclerView, 4);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private FragmentListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[1]
            , (android.widget.ImageView) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.recyclerview.widget.RecyclerView) bindings[4]
            , (android.widget.TextView) bindings[3]
            );
        this.floatingActionButton.setTag(null);
        this.ivNoData.setTag(null);
        this.listLayout.setTag(null);
        this.tvNoData.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.mSharedViewModel == variableId) {
            setMSharedViewModel((id.co.todoapp.fragments.SharedViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setMSharedViewModel(@Nullable id.co.todoapp.fragments.SharedViewModel MSharedViewModel) {
        this.mMSharedViewModel = MSharedViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.mSharedViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeMSharedViewModelEmptyDatabase((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeMSharedViewModelEmptyDatabase(androidx.lifecycle.MutableLiveData<java.lang.Boolean> MSharedViewModelEmptyDatabase, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> mSharedViewModelEmptyDatabase = null;
        id.co.todoapp.fragments.SharedViewModel mSharedViewModel = mMSharedViewModel;
        java.lang.Boolean mSharedViewModelEmptyDatabaseGetValue = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (mSharedViewModel != null) {
                    // read mSharedViewModel.emptyDatabase
                    mSharedViewModelEmptyDatabase = mSharedViewModel.getEmptyDatabase();
                }
                updateLiveDataRegistration(0, mSharedViewModelEmptyDatabase);


                if (mSharedViewModelEmptyDatabase != null) {
                    // read mSharedViewModel.emptyDatabase.getValue()
                    mSharedViewModelEmptyDatabaseGetValue = mSharedViewModelEmptyDatabase.getValue();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            id.co.todoapp.fragments.BindingAdapters.navigateToAddFragment(this.floatingActionButton, true);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            id.co.todoapp.fragments.BindingAdapters.emptyDatabase(this.ivNoData, mSharedViewModelEmptyDatabase);
            id.co.todoapp.fragments.BindingAdapters.emptyDatabase(this.tvNoData, mSharedViewModelEmptyDatabase);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): mSharedViewModel.emptyDatabase
        flag 1 (0x2L): mSharedViewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}